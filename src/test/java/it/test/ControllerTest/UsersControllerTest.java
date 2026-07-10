package it.test.ControllerTest;

import it.classe.SpringClass.Controller.UsersController;
import it.classe.SpringClass.Dto.UsersDto;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;



@WebMvcTest(UsersController.class)
@ContextConfiguration(classes = SpringClassApplication.class)
class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsersService usersService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsersDto usersDto;

    @BeforeEach
    void setUp() {
        usersDto = new UsersDto(
                1,
                "mario",
                "1234",
                "mario@mario.it"
        );
    }

    @Test
    void findAll() throws Exception {

        List<UsersDto> usersList = List.of(usersDto);

        when(usersService.getAll())
                .thenReturn(usersList);


        // Act + Assert
        mockMvc.perform(get("/users/getall"))
                .andExpect(status().isOk())
                .andExpect(result -> {

                    String json = result.getResponse().getContentAsString();

                    UsersDto[] response =
                            objectMapper.readValue(json, UsersDto[].class);


                    assertEquals(1, response.length);

                    UsersDto returnedUser = response[0];

                    assertEquals(usersDto.getId(), returnedUser.getId());
                    assertEquals(usersDto.getUsername(), returnedUser.getUsername());
                    assertEquals(usersDto.getPassword(), returnedUser.getPassword());
                    assertEquals(usersDto.getEmail(), returnedUser.getEmail());
                });
    }

    @Test
    void getDatabaseUrl_shouldReturnUrl() throws Exception {

        when(usersService.getDatabaseUrl())
                .thenReturn("jdbc:mysql://localhost:3306/test");


        mockMvc.perform(get("/users/url"))
                .andExpect(status().isOk())
                .andExpect( result -> {
                    content().string("jdbc:mysql://localhost:3306/test");

                        });


    }
/*
    @Test
    void getDatabaseUrl_shouldReturn500WhenServiceFails() throws Exception {

        when(usersService.getDatabaseUrl())
                .thenThrow(new RuntimeException("Database offline"));


        mockMvc.perform(get("/users/url"))
                .andExpect(status().isInternalServerError());
    }
*/
    @Test
    void findAll_shouldReturnUsers() throws Exception {




        when(usersService.getAll())
                .thenReturn(List.of(usersDto));


        mockMvc.perform(get("/users/getall"))
                .andExpect(status().isOk())
                .andExpect(result ->{ jsonPath("$[0].username")
                        .value("mario");
                });
    }


    @Test
    void findAll_shouldReturnEmptyList() throws Exception {

        when(usersService.getAll())
                .thenReturn(Collections.emptyList());


        mockMvc.perform(get("/users/getall"))
                .andExpect(status().isOk())
                .andExpect(x-> {jsonPath("$").isEmpty();
                });
    }
/*
    @Test
    void findAll_shouldReturn500WhenServiceFails() throws Exception {


        when(usersService.getAll())
                .thenThrow(new RuntimeException());


        mockMvc.perform(get("/users/getall"))
                .andExpect(status().isInternalServerError());
    }
*/
    @Test
    void findById_shouldReturnUser() throws Exception {





        when(usersService.read(1))
                .thenReturn(usersDto);


        mockMvc.perform(get("/users/read").queryParam("id", "1"))
                .andExpect(status().isOk())
                .andExpect(x->jsonPath("$.username")
                        .value("mario"));
    }

    @Test
    void findById_shouldReturn404() throws Exception {


        when(usersService.read(99))
                .thenReturn(null);


        mockMvc.perform(get("/users").queryParam("id", "99"))
                .andExpect(status().isNotFound());
    }
/*
    @Test
    void createUser_shouldReturnCreated() throws Exception {


        UsersDto dto = new UsersDto(
                1,
                "pippo",
                "2222",
                "pippo@mail.it",
                new ArrayList<>()
        );


        when(usersService.insert(any()))
                .thenReturn(dto);


        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

 */
}