package it.test.ControllerTest;

import it.classe.SpringClass.Controller.AutoController;
import it.classe.SpringClass.Controller.UsersController;
import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Dto.AutoDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Service.AutoService;
import it.classe.SpringClass.Service.UsersService;
import it.classe.SpringClass.SpringClassApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;






@WebMvcTest(AutoController.class)
@ContextConfiguration(classes = SpringClassApplication.class)
public class AutoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AutoService autoService;

    @Autowired
    private ObjectMapper objectMapper;

    AutoDto autoDto ;



    @BeforeEach
    void setUp() {
        autoDto = new AutoDto(1,
                2001,
                "fiat",
                "panda",
                "grigio",
                new AlunnoDto(),
                new UsersDto()
        );

    }


    @Test
    void findAll() throws Exception {

        // Arrange

        List<AutoDto> autoList = List.of(autoDto);

        when(autoService.getAll())
                .thenReturn(autoList);


        // Act + Assert
        mockMvc.perform(get("/auto/getall"))
                .andExpect(status().isOk())
                .andExpect(result -> {

                    String json = result.getResponse().getContentAsString();

                    AutoDto[] response =
                            objectMapper.readValue(json, AutoDto[].class);


                    assertEquals(1, response.length);

                    AutoDto returnedAuto = response[0];

                    assertEquals(autoDto.getId(), returnedAuto.getId());
                    assertEquals(autoDto.getAnno(), returnedAuto.getAnno());
                    assertEquals(autoDto.getMarca(), returnedAuto.getMarca());
                    assertEquals(autoDto.getModello(), returnedAuto.getModello());
                    assertEquals(autoDto.getColore(), returnedAuto.getColore());
                });
    }


    @Test
    void findById() throws Exception {

        when(autoService.read(1))
                .thenReturn(autoDto);



        mockMvc.perform(get("/auto/read")
                        .queryParam("id", "1"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    AutoDto response = objectMapper.readValue(json, AutoDto.class);

                    assertEquals(autoDto, response);
                });

    }
}
