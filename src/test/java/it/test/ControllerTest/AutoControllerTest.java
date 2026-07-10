package it.test.ControllerTest;

import it.classe.SpringClass.Controller.UsersController;
import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Dto.AutoDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Service.AutoService;
import it.classe.SpringClass.Service.UsersService;
import it.classe.SpringClass.SpringClassApplication;
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

@WebMvcTest(AutoControllerTest.class)
@ContextConfiguration(classes = SpringClassApplication.class)




public class AutoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AutoService autoService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void findAll() throws Exception {

        AutoDto autoDto = new AutoDto(
                1,
                2001,
                "fiat",
                "panda",
                "grigio",
                new AlunnoDto(),
                new UsersDto()
        );

        List<AutoDto> autoList = List.of(autoDto);

        when(this.AutoService.getAll())
                .thenReturn(autoList);


        // Act + Assert
        mockMvc.perform(get("/autos/getall"))
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
                });
    }
}
