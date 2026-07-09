package it.test.ControllerTest;

import it.classe.SpringClass.Controller.ContoCorrenteController;
import it.classe.SpringClass.Service.ContoCorrenteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContoCorrenteController.class)
public class ContoCorrenteControllerTest {



        @MockitoBean
        private ContoCorrenteService contoService;

            @Autowired
            private MockMvc mockMvc;

        @Test
        void returnDatabaseUrl() throws Exception{

            when(contoService.getDatabaseUrl())
                    .thenReturn("jdbc:postgresql://localhost:5432/user");
        }

        @Test
        void getContoByEmail(){
            mockMvc.perform(get("/contoCorrente/url"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("jdbc:postgresql://localhost:5432/user"));

       verify(contoService).getDatabaseUrl();
        }

}
