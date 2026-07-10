package it.test.ServiceTest;

import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Mapper.OrdineMapper;
import it.classe.SpringClass.Model.Ordine;
import it.classe.SpringClass.Repository.OrdineRepository;
import it.classe.SpringClass.Service.OrdineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrdineServiceTest {

    private  final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrdineServiceTest.class);

    @Mock
    private OrdineRepository ordineRepository;

    @Mock
    private OrdineMapper ordineMapper;


    @InjectMocks
    private OrdineService ordineService;


    @Test
    void insert_positive() {

        OrdineDto dto = new OrdineDto(
                1,
                "Laptop",
                LocalDateTime.now(),
                1000,
                1,
                null
        );


        Ordine ordine = new Ordine(
                1,
                "Laptop",
                dto.getDataCreazione(),
                1000,
                1,
                null
        );


        when(ordineMapper.toEntity(dto))
                .thenReturn(ordine);


        when(ordineRepository.save(ordine))
                .thenReturn(ordine);


        when(ordineMapper.toDTO(ordine))
                .thenReturn(dto);



        OrdineDto result = ordineService.insert(dto);



        assertNotNull(result);
        assertEquals(dto, result);


        verify(ordineMapper).toEntity(dto);
        verify(ordineRepository).save(ordine);
        verify(ordineMapper).toDTO(ordine);
    }


    @Test
    void insert_negative() {

        OrdineDto dto = new OrdineDto(
                1,
                "Laptop",
                LocalDateTime.now(),
                1000,
                1,
                null
        );


        Ordine ordine = new Ordine(
                1,
                "Laptop",
                dto.getDataCreazione(),
                1000,
                1,
                null
        );


        when(ordineMapper.toEntity(dto))
                .thenReturn(ordine);


        when(ordineRepository.save(ordine))
                .thenThrow(new RuntimeException("Errore salvataggio ordine"));



        assertThrows(
                RuntimeException.class,
                () -> ordineService.insert(dto)
        );



        verify(ordineMapper)
                .toEntity(dto);


        verify(ordineRepository)
                .save(ordine);
    }


    @Test
    void update_positive() {

        OrdineDto dto = new OrdineDto(
                1,
                "PC",
                LocalDateTime.now(),
                2000,
                1,
                null
        );


        Ordine ordine = new Ordine(
                1,
                "PC",
                dto.getDataCreazione(),
                2000,
                1,
                null
        );


        when(ordineMapper.toEntity(dto))
                .thenReturn(ordine);


        when(ordineRepository.save(ordine))
                .thenReturn(ordine);


        when(ordineMapper.toDTO(ordine))
                .thenReturn(dto);



        OrdineDto result = ordineService.update(dto);



        assertNotNull(result);
        assertEquals(dto,result);


        verify(ordineRepository)
                .save(ordine);
    }

    @Test
    void update_negative() {


        OrdineDto dto = new OrdineDto(
                1,
                "PC",
                LocalDateTime.now(),
                2000,
                1,
                null
        );


        Ordine ordine = new Ordine(
                1,
                "PC",
                dto.getDataCreazione(),
                2000,
                1,
                null
        );


        when(ordineMapper.toEntity(dto))
                .thenReturn(ordine);


        when(ordineRepository.save(ordine))
                .thenThrow(new RuntimeException("Errore aggiornamento"));



        assertThrows(
                RuntimeException.class,
                () -> ordineService.update(dto)
        );



        verify(ordineMapper)
                .toEntity(dto);


        verify(ordineRepository)
                .save(ordine);
    }

    @Test
    void delete_positive(){

        Integer id = 1;


        doNothing()
                .when(ordineRepository)
                .deleteById(id);



        ordineService.delete(id);



        verify(ordineRepository)
                .deleteById(id);
    }

    @Test
    void delete_negative() {

        Integer id = 99;


        doThrow(new RuntimeException("Ordine non trovato"))
                .when(ordineRepository)
                .deleteById(id);



        assertThrows(
                RuntimeException.class,
                () -> ordineService.delete(id)
        );



        verify(ordineRepository)
                .deleteById(id);
    }

    @Test
    void read_positive(){

        Ordine ordine = new Ordine(
                1,
                "Tablet",
                LocalDateTime.now(),
                500,
                1,
                null
        );


        OrdineDto dto = new OrdineDto(
                1,
                "Tablet",
                ordine.getDataCreazione(),
                500,
                1,
                null
        );


        when(ordineRepository.findById(1))
                .thenReturn(Optional.of(ordine));


        when(ordineMapper.toDTO(ordine))
                .thenReturn(dto);



        OrdineDto result = ordineService.read(1);



        assertNotNull(result);
        assertEquals(dto,result);


        verify(ordineRepository)
                .findById(1);

        verify(ordineMapper)
                .toDTO(ordine);
    }

    @Test
    void read_negative() {


        Integer id = 99;


        when(ordineRepository.findById(id))
                .thenReturn(Optional.empty());



        assertThrows(
                NoSuchElementException.class,
                () -> ordineService.read(id)
        );



        verify(ordineRepository)
                .findById(id);
    }


    @Test
    void findByImporto_repositoryException(){

        when(ordineRepository.findByImporto(1000))
                .thenThrow(new RuntimeException("Database error"));


        assertThrows(
                RuntimeException.class,
                () -> ordineService.findByImporto(1000)
        );


        verify(ordineRepository)
                .findByImporto(1000);
    }

    @Test
    void findByQuantita_nullResult(){

        when(ordineRepository.findByQuantita(10))
                .thenReturn(null);


        when(ordineMapper.toDTOList(null))
                .thenReturn(null);



        List<OrdineDto> result =
                ordineService.findByQuantita(10);



        assertNull(result);
    }


    @Test
    void findAll_positive() {

        Ordine ordine = new Ordine(
                1,
                "Laptop",
                LocalDateTime.now(),
                100.0,
                2,
                null
        );

        List<Ordine> ordini = List.of(ordine);


        OrdineDto dto = new OrdineDto(
                1,
                "Laptop",
                ordine.getDataCreazione(),
                100.0,
                2,
                null
        );

        List<OrdineDto> expected = List.of(dto);


        when(ordineRepository.findAll())
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(expected);



        List<OrdineDto> result = (List<OrdineDto>) ordineService.getAll();



        assertNotNull(result);
        assertEquals(expected, result);


        verify(ordineRepository).findAll();
        verify(ordineMapper).toDTOList(ordini);
    }

    @Test
    void findAll_negative() {

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> expected = new ArrayList<>();


        when(ordineRepository.findAll())
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(expected);



        List<OrdineDto> result = (List<OrdineDto>) ordineService.getAll();



        assertNotNull(result);
        assertTrue(result.isEmpty());


        verify(ordineRepository).findAll();
        verify(ordineMapper).toDTOList(ordini);
    }


    @Test
    void findByDataCreazione_positive() {

        LocalDateTime data = LocalDateTime.now();


        Ordine ordine = new Ordine(
                1,
                "Telefono",
                data,
                300.0,
                1,
                null
        );


        OrdineDto dto = new OrdineDto(
                1,
                "Telefono",
                data,
                300.0,
                1,
                null
        );


        when(ordineRepository.findByDataCreazione(data))
                .thenReturn(ordine);

        when(ordineMapper.toDTO(ordine))
                .thenReturn(dto);



        OrdineDto result = ordineService.findByDataCreazione(data);



        assertNotNull(result);
        assertEquals(dto, result);


        verify(ordineRepository).findByDataCreazione(data);
        verify(ordineMapper).toDTO(ordine);
    }



    @Test
    void findByDataCreazione_negative() {

        LocalDateTime data = LocalDateTime.now();


        when(ordineRepository.findByDataCreazione(data))
                .thenReturn(null);


        when(ordineMapper.toDTO(null))
                .thenReturn(null);



        OrdineDto result = ordineService.findByDataCreazione(data);



        assertNull(result);


        verify(ordineRepository).findByDataCreazione(data);
        verify(ordineMapper).toDTO(null);
    }



    @Test
    void findByImporto_positive() {

        Ordine ordine = new Ordine(
                1,
                "PC",
                LocalDateTime.now(),
                1000.0,
                1,
                null
        );


        List<Ordine> ordini = List.of(ordine);


        List<OrdineDto> dtoList = List.of(
                new OrdineDto(
                        1,
                        "PC",
                        ordine.getDataCreazione(),
                        1000.0,
                        1,
                        null
                )
        );


        when(ordineRepository.findByImporto(1000.0))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        List<OrdineDto> result =
                ordineService.findByImporto(1000.0);



        assertFalse(result.isEmpty());
        assertEquals(1,result.size());


        verify(ordineRepository).findByImporto(1000.0);
        verify(ordineMapper).toDTOList(ordini);
    }



    @Test
    void findByImporto_negative() {

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();


        when(ordineRepository.findByImporto(9999.0))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        List<OrdineDto> result =
                ordineService.findByImporto(9999.0);



        assertNotNull(result);
        assertTrue(result.isEmpty());


        verify(ordineRepository).findByImporto(9999.0);
        verify(ordineMapper).toDTOList(ordini);
    }



    @Test
    void findByQuantita_positive() {

        // Arrange
        Ordine ordine = new Ordine(
                1,
                "Laptop",
                LocalDateTime.now(),
                80.0,
                10,
                null
        );


        List<Ordine> ordini = List.of(ordine);


        List<OrdineDto> dtoList = List.of(
                new OrdineDto(
                        1,
                        "Laptop",
                        ordine.getDataCreazione(),
                        80.0,
                        10,
                        null
                )
        );


        when(ordineRepository.findByQuantita(10))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.findByQuantita(10);



        // Assert
        assertNotNull(result);
        assertEquals(dtoList, result);
        assertEquals(1, result.size());


        verify(ordineRepository)
                .findByQuantita(10);

        verify(ordineMapper)
                .toDTOList(ordini);
    }



    @Test
    void findByQuantita_negative() {

        // Arrange
        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();


        when(ordineRepository.findByQuantita(100))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.findByQuantita(100);



        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());


        verify(ordineRepository)
                .findByQuantita(100);

        verify(ordineMapper)
                .toDTOList(ordini);
    }



    @Test
    void cercaProdotti_positive() {

        // Arrange
        String prodotto = "Laptop";


        Ordine ordine = new Ordine(
                1,
                prodotto,
                LocalDateTime.now(),
                500.0,
                1,
                null
        );


        List<Ordine> ordini = List.of(ordine);


        List<OrdineDto> dtoList = List.of(
                new OrdineDto(
                        1,
                        prodotto,
                        ordine.getDataCreazione(),
                        500.0,
                        1,
                        null
                )
        );


        when(ordineRepository.cercaProdotti(prodotto))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.cercaProdotti(prodotto);



        // Assert
        assertNotNull(result);
        assertEquals(dtoList, result);
        assertEquals(1, result.size());


        verify(ordineRepository)
                .cercaProdotti(prodotto);

        verify(ordineMapper)
                .toDTOList(ordini);
    }



    @Test
    void cercaProdotti_negative() {

        // Arrange
        String prodotto = "Tablet";


        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();


        when(ordineRepository.cercaProdotti(prodotto))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.cercaProdotti(prodotto);



        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());


        verify(ordineRepository)
                .cercaProdotti(prodotto);

        verify(ordineMapper)
                .toDTOList(ordini);
    }

    @Test
    void ordiniDalPiuCostoso_positive() {

        // Arrange
        Ordine ordine = new Ordine(
                1,
                "Computer",
                LocalDateTime.now(),
                1500.0,
                1,
                null
        );


        List<Ordine> ordini = List.of(ordine);


        List<OrdineDto> dtoList = List.of(
                new OrdineDto(
                        1,
                        "Computer",
                        ordine.getDataCreazione(),
                        1500.0,
                        1,
                        null
                )
        );


        when(ordineRepository.ordiniDalPiuCostoso())
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.ordiniDalPiuCostoso();



        // Assert
        assertNotNull(result);
        assertEquals(dtoList, result);
        assertEquals(1, result.size());


        verify(ordineRepository)
                .ordiniDalPiuCostoso();

        verify(ordineMapper)
                .toDTOList(ordini);
    }



    @Test
    void ordiniDalPiuCostoso_negative() {

        // Arrange
        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();


        when(ordineRepository.ordiniDalPiuCostoso())
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.ordiniDalPiuCostoso();



        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());


        verify(ordineRepository)
                .ordiniDalPiuCostoso();

        verify(ordineMapper)
                .toDTOList(ordini);
    }


    @Test
    void cercaPerQuantitaMaggiore_positive() {

        // Arrange
        int quantita = 5;


        Ordine ordine = new Ordine(
                1,
                "Mouse",
                LocalDateTime.now(),
                50.0,
                10,
                null
        );


        List<Ordine> ordini = List.of(ordine);


        List<OrdineDto> dtoList = List.of(
                new OrdineDto(
                        1,
                        "Mouse",
                        ordine.getDataCreazione(),
                        50.0,
                        10,
                        null
                )
        );


        when(ordineRepository.cercaPerQuantitaMaggiore(quantita))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.cercaPerQuantitaMaggiore(quantita);



        // Assert
        assertNotNull(result);
        assertEquals(dtoList, result);
        assertEquals(1, result.size());


        verify(ordineRepository)
                .cercaPerQuantitaMaggiore(quantita);

        verify(ordineMapper)
                .toDTOList(ordini);
    }



    @Test
    void cercaPerQuantitaMaggiore_negative() {

        // Arrange
        int quantita = 100;


        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();


        when(ordineRepository.cercaPerQuantitaMaggiore(quantita))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.cercaPerQuantitaMaggiore(quantita);



        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());


        verify(ordineRepository)
                .cercaPerQuantitaMaggiore(quantita);

        verify(ordineMapper)
                .toDTOList(ordini);
    }

    @Test
    void ultimiOrdini_positive() {

        // Arrange
        Integer idUsers = 1;


        Ordine ordine = new Ordine(
                1,
                "Smartphone",
                LocalDateTime.now(),
                800.0,
                1,
                null
        );


        List<Ordine> ordini = List.of(ordine);


        List<OrdineDto> dtoList = List.of(
                new OrdineDto(
                        1,
                        "Smartphone",
                        ordine.getDataCreazione(),
                        800.0,
                        1,
                        null
                )
        );


        when(ordineRepository.ultimiOrdini(idUsers))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.ultimiOrdini(idUsers);



        // Assert
        assertNotNull(result);
        assertEquals(dtoList, result);
        assertEquals(1, result.size());


        verify(ordineRepository)
                .ultimiOrdini(idUsers);

        verify(ordineMapper)
                .toDTOList(ordini);
    }



    @Test
    void ultimiOrdini_negative() {

        // Arrange
        Integer idUsers = 99;


        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();


        when(ordineRepository.ultimiOrdini(idUsers))
                .thenReturn(ordini);


        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);



        // Act
        List<OrdineDto> result =
                ordineService.ultimiOrdini(idUsers);



        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());


        verify(ordineRepository)
                .ultimiOrdini(idUsers);

        verify(ordineMapper)
                .toDTOList(ordini);
    }
}
