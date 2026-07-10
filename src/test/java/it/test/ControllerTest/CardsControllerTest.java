package it.test.ControllerTest;

import it.classe.SpringClass.Controller.CardsController;
import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Service.CardsService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardsController.class)
public class CardsControllerTest {

    private  final Logger log = org.slf4j.LoggerFactory.getLogger(CardsControllerTest.class);

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CardsService cardsService;

    private CardsDto createCardsDto(){
        CardsDto cards=new CardsDto();
        cards.setId(1);
        cards.setTelefono("1234");
        cards.setCardNumber("0001");
        cards.setCartType("mastercard");
        cards.setAmount(1000);
        cards.setAvailableAmount(800);
        UsersDto users=new UsersDto();
        users.setId(1);
        users.setUsername("Ciao");
        users.setPassword("ciao");
        users.setEmail("ciao@ciao.com");

        cards.setUsers(users);
        return cards;
    }


    @Test
    void findByTelefono() throws Exception {

        CardsDto cardsDto=createCardsDto();

        log.info("Creato cards dto {}", cardsDto);

        when(cardsService.findByTelefono("1234"))
                .thenReturn(cardsDto);

        mockMvc.perform(get("/cards/telefono")
                        .param("telefono", "1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.telefono").value("1234"))
                .andExpect(jsonPath("$.cardNumber").value("0001"))
                .andExpect(jsonPath("$.cartType").value("masterCard"));

        verify(cardsService, times(1))
                .findByTelefono("1234");
    }

    @Test
    void findByCartType() throws Exception {
        List<CardsDto> cardsDto= Collections.singletonList(createCardsDto());
        log.info("Creato cards dto {}", cardsDto);
        when(cardsService.findByCartType("mastercard"))
                .thenReturn(cardsDto);
        mockMvc.perform(get("/cards/type")
                .param("cartType","mastercard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cartType").value("mastercard"));

        verify(cardsService,times(1))
                .findByCartType("mastercard");
    }

    @Test
    void findByTelefono_NotFound_ShouldReturnNullOrStatus() throws Exception {
        // database non trova nessuna carta associata al telefono
        when(cardsService.findByTelefono("9999")).thenReturn(null);
        mockMvc.perform(get("/telefono")
                        .param("telefono", "9999"))
                // Se il tuo controller restituisce semplicemente null, risponderà comunque 200 OK vuoto.
                // Se gestisci l'assenza con un'eccezione custom, cambia l'asserzione in .isNotFound()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
        verify(cardsService, times(1)).findByTelefono("9999");
    }

    @Test
    void findByCartType_ServerError_ShouldReturn500() throws Exception {
        // Simuliamo un errore improvviso del database o del service layer
        when(cardsService.findByCartType("mastercard"))
                .thenThrow(new RuntimeException("Database temporaneamente offline"));
        mockMvc.perform(get("/type")
                        .param("cartType", "mastercard"))
                .andExpect(status().isInternalServerError());
        verify(cardsService, times(1)).findByCartType("mastercard");
    }


}
