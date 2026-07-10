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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
        when(cardsMapper.toDTOList(listEntity)).thenReturn(cardsDtos);
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

    @Test
    void findByTelefono(){
        Cards cards = createCards();
        CardsDto cardsDto = createCardsDto();

        when(cardsRepository.findByTelefono("1234")).thenReturn(cards);
        when(cardsMapper.toDTO(cards)).thenReturn(cardsDto);

        CardsDto result = cardsService.findByTelefono("1234");

        assertNotNull(result);
        assertEquals("1234", result.getTelefono());
        verify(cardsRepository, times(1)).findByTelefono("1234");
    }

    @Test
    void findByAmount() {
        Cards cards = createCards();
        CardsDto cardsDto = createCardsDto();
        List<Cards> cardsList = List.of(cards);
        List<CardsDto> dtoList = List.of(cardsDto);

        when(cardsRepository.findByAmount(1000)).thenReturn(cardsList);
        when(cardsMapper.toDTOList(cardsList)).thenReturn(dtoList);

        List<CardsDto> result = cardsService.findByAmount(1000);

        assertEquals(1, result.size());
        assertEquals(1000, result.get(0).getAmount());
    }

    @Test
    void findByAvailableAmount() {
        Cards cards = createCards();
        CardsDto cardsDto = createCardsDto();
        List<Cards> cardsList = List.of(cards);
        List<CardsDto> dtoList = List.of(cardsDto);

        when(cardsRepository.findByAvailableAmount(800)).thenReturn(cardsList);
        when(cardsMapper.toDTOList(cardsList)).thenReturn(dtoList);

        List<CardsDto> result = cardsService.findByAvailableAmount(800);

        assertEquals(1, result.size());
        assertEquals(800, result.get(0).getAvailableAmount());
    }

    @Test
    void updateTelefono() throws Exception {
        Cards cards = createCards(); // ID = 1, vecchio telefono = "1234"
        Cards updatedCards = createCards();
        updatedCards.setTelefono("5678"); // Nuovo telefono modificato

        CardsDto updatedDto = createCardsDto();
        updatedDto.setTelefono("5678");

        // findById per controllare se esiste
        when(cardsRepository.findById(1)).thenReturn(Optional.of(cards));
        when(cardsRepository.save(any(Cards.class))).thenReturn(updatedCards);
        when(cardsMapper.toDTO(updatedCards)).thenReturn(updatedDto);

        CardsDto result = cardsService.changeTelefono(1, "5678");

        assertNotNull(result);
        assertEquals("5678", result.getTelefono());
        verify(cardsRepository, times(1)).save(any(Cards.class));
    }

    @Test
    void findByCardNumber_NotFound_ReturnsNull() {
        // database a restituire null (carta non trovata)
        when(cardsRepository.findByCardNumber("9999")).thenReturn(null);

        CardsDto result = cardsService.findByCardNumber("9999");

        // Se la logica del tuo service ritorna null (o lancia eccezione, adatta di conseguenza)
        assertNull(result);
        verify(cardsMapper, never()).toDTO(any());
    }

    @Test
    void findByCartType_NoMatch_ReturnsEmptyList() {
        //lista vuota se non ce
        when(cardsRepository.findByCartType("AmericanExpress")).thenReturn(Collections.emptyList());
        when(cardsMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<CardsDto> result = cardsService.findByCartType("AmericanExpress");

        assertTrue(result.isEmpty());
    }

    @Test
    void updateTelefono_CardNotFound_ThrowsException() {
        // id or vuoto
        when(cardsRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            cardsService.changeTelefono(99, "5678");
        });

        //mi serve per non chaiamre il db visto lerrore
        verify(cardsRepository, never()).save(any(Cards.class));
    }
}
