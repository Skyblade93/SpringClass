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
                .andExpect(jsonPath("$.cartType").value("MasterCard"));

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
                .andExpect(jsonPath("$.cartType").value("mastercard"));

        verify(cardsService,times(1))
                .findByCartType("mastercard");

    }


}
