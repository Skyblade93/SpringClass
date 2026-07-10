package it.test.ControllerTest;

import it.classe.SpringClass.Controller.ContoCorrenteController;
import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Service.ContoCorrenteService;
import it.classe.SpringClass.SpringClassApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContoCorrenteController.class)
@ContextConfiguration(classes = SpringClassApplication.class)
class ContoCorrenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContoCorrenteService contoCorrenteService;

    @Test
    void shouldReturnDatabaseUrl() throws Exception {

        // Arrange
        when(contoCorrenteService.getDatabaseUrl())
                .thenReturn("jdbc:postgresql://localhost:5432/user");

        // Act + Assert
        mockMvc.perform(get("/contoCorrente/url"))
                .andExpect(status().isOk())
                .andExpect(content().string("jdbc:postgresql://localhost:5432/user"));

        verify(contoCorrenteService).getDatabaseUrl();
    }

    @Test
    void shouldReturnInternalServerErrorWhenDatabaseFails() throws Exception {

        // Arrange
        when(contoCorrenteService.getDatabaseUrl())
                .thenThrow(new RuntimeException("Database offline"));

        // Act + Assert
        mockMvc.perform(get("/contoCorrente/url"))
                .andExpect(status().isInternalServerError());

        verify(contoCorrenteService).getDatabaseUrl();
    }

    @Test
    void shouldReturnContoByEmail() throws Exception {

        // Arrange
        ContoCorrenteDto dto = new ContoCorrenteDto();
        dto.setIdConto(1);
        dto.setEmail("mario@mario.it");

        when(contoCorrenteService.findByEmail("mario@mario.it"))
                .thenReturn(dto);

        // Act + Assert
        mockMvc.perform(get("/contoCorrente/email/mario@mario.it"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idConto").value(1))
                .andExpect(jsonPath("$.email").value("mario@mario.it"));

        verify(contoCorrenteService).findByEmail("mario@mario.it");
    }

    @Test
    void shouldReturnNullWhenEmailDoesNotExist() throws Exception {

        // Arrange
        when(contoCorrenteService.findByEmail("inesistente@mail.it"))
                .thenReturn(null);

        // Act + Assert
        mockMvc.perform(get("/contoCorrente/email/inesistente@mail.it"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(contoCorrenteService).findByEmail("inesistente@mail.it");
    }

    @Test
    void shouldReturnInternalServerErrorWhenFindByEmailFails() throws Exception {

        // Arrange
        when(contoCorrenteService.findByEmail("errore@mail.it"))
                .thenThrow(new RuntimeException("Errore"));

        // Act + Assert
        mockMvc.perform(get("/contoCorrente/email/errore@mail.it"))
                .andExpect(status().isInternalServerError());

        verify(contoCorrenteService).findByEmail("errore@mail.it");
    }

        @Test
        void shouldReturnContoByCognome() throws Exception {
                ContoCorrenteDto contoCorrenteDto=new ContoCorrenteDto(1,
                        "IT4534333334235635",
                        "Nicola",
                        "Sposito",
                        "sposito@gmail.com",
                        "223",new ArrayList<>());

                when(contoCorrenteService.findByCognome("Sposito")).thenReturn(contoCorrenteDto);

            mockMvc.perform(get("/contoCorrente/cognome/Sposito"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cognome").value("Sposito"));

        }


    @Test
    void shouldReturnNullWhenCognomeDoesNotExist() throws Exception {
        ContoCorrenteDto contoCorrenteDto=new ContoCorrenteDto(1,
                "IT4534333334235635",
                "Nicola",
                null,
                "sposito@gmail.com",
                "223",new ArrayList<>());

        when(contoCorrenteService.findByCognome("Sposito")).thenReturn(contoCorrenteDto)
                .thenThrow(new Exception("Cognome non trovato "));

        mockMvc.perform(get("/contoCorrente/cognome/Sposito"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cognome").value("Sposito"));
               // .andExpect(jsonPath("$.cognome").doesNotExist());
            verify(contoCorrenteService).findByCognome("Sposito");
    }
}