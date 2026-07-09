package it.test.ServiceTest;

import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Mapper.CardsMapper;
import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Repository.CardsRepository;
import it.classe.SpringClass.Service.CardsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class CardsServiceTest {

    private  final Logger log = org.slf4j.LoggerFactory.getLogger(CardsServiceTest.class);

    @Mock
    private CardsRepository cardsRepository;

    @Mock
    private CardsMapper cardsMapper;

    @InjectMocks
    private CardsService cardsService;


    private Cards createCards(){
        Cards cards=new Cards();
        cards.setId(1);
        cards.setCartType("mastercard");
        cards.setTelefono("1234");
        cards.setCardNumber("0001");
        cards.setAmount(1000);
        cards.setAvailableAmount(800);
        Users users=new Users();
        users.setId(1);
        users.setUsername("Ciao");
        users.setPassword("ciao");
        users.setEmail("ciao@ciao.com");

        cards.setUsers(users);
        return cards;
    }


    private CardsDto createCardsDto(){
        CardsDto cardsDto=new CardsDto();
        cardsDto.setId(1);
        cardsDto.setCartType("mastercard");
        cardsDto.setTelefono("1234");
        cardsDto.setCardNumber("0001");
        cardsDto.setAmount(1000);
        cardsDto.setAvailableAmount(800);
        UsersDto usersDto=new UsersDto();
        usersDto.setId(1);
        usersDto.setUsername("Ciao");
        usersDto.setPassword("ciao");
        usersDto.setEmail("ciao@ciao.com");

        cardsDto.setUsers(usersDto);
        return cardsDto;
    }

    @Test
    void findAll(){
        Cards cards=createCards();
        CardsDto cardsDto=createCardsDto();

        List<Cards> listEntity=List.of(cards);
        List<CardsDto> cardsDtos=List.of(cardsDto);

        when(cardsRepository.findAll()).thenReturn(listEntity);
        log.info("Finding all cards "+cards);
        when(cardsMapper.toDTOList((Iterable<Cards>) cards)).thenReturn(cardsDtos);
        // Act
        List<CardsDto> actual = new ArrayList<>();
        cardsService.getAll().forEach(actual::add);
        log.info("aggiungi tutte le cards" + actual);

        // Assert
        assertEquals(cardsDtos, actual);

        verify(cardsRepository, times(1)).findAll();
        verify(cardsMapper, times(1)).toDTOList(listEntity);
        verifyNoMoreInteractions(cardsRepository, cardsMapper);
    }

    @Test
    void findByCartType(){
        Cards cards = createCards();
        Cards cards2 = createCards();

        CardsDto cardsDto = createCardsDto();
        CardsDto cardsDto2 = createCardsDto();

        List<Cards> listEntity = List.of(cards, cards2);
        List<CardsDto> cardsDtos = List.of(cardsDto, cardsDto2);

        when(cardsRepository.findByCartType(cards.getCartType()))
                .thenReturn(listEntity);

        when(cardsMapper.toDTOList(listEntity))
                .thenReturn(cardsDtos);

        List<CardsDto> result = cardsService.findByCartType(cards.getCartType());

        log.info("carte trovate {}", result);

        assertEquals(2, result.size());
        assertEquals(cardsDto, result.get(0));
        assertEquals(cardsDto2, result.get(1));

        verify(cardsRepository, times(1))
                .findByCartType(cards.getCartType());

        verify(cardsMapper, times(1))
                .toDTOList(listEntity);
    }

    @Test
    void findByCardNumber(){
        Cards cards=createCards();
        CardsDto cardsDto=createCardsDto();

        when(cardsRepository.findByCardNumber(cards.getCardNumber()))
                .thenReturn(cards);
        when(cardsMapper.toDTO(cards))
                .thenReturn(cardsDto);

        CardsDto result = cardsService.findByCardNumber(cards.getCardNumber());

        log.info("Carta trovata {}", result);

        assertEquals(cardsDto, result);

        verify(cardsRepository, times(1))
                .findByCardNumber(cards.getCardNumber());

        verify(cardsMapper, times(1))
                .toDTO(cards);
    }
}
