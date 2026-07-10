package it.test.ServiceTest;

import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Mapper.OrdineMapper;
import it.classe.SpringClass.Model.Ordine;
import it.classe.SpringClass.Repository.OrdineRepository;
import it.classe.SpringClass.Service.OrdineService;
import org.junit.jupiter.api.BeforeEach;
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

    private Ordine ordine1;

    private OrdineDto ordineDto;

    LocalDateTime data = LocalDateTime.of(2026, 7, 9, 10, 30);

    @BeforeEach
    void setUp() {
        ordine1 = new Ordine(
                1,
                "Laptop",
                data,
                1000,
                1,
                null
        );

        ordineDto= new OrdineDto(
                2,
                "Mouse",
                data,
                1000,
                2,
                null
        );

    }


    @Test
    void insert_positive() {

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

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

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

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

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

        when(ordineMapper.toEntity(dto))
                .thenReturn(ordine);

        when(ordineRepository.save(ordine))
                .thenReturn(ordine);

        when(ordineMapper.toDTO(ordine))
                .thenReturn(dto);

        OrdineDto result = ordineService.update(dto);

        assertNotNull(result);
        assertEquals(dto,result);

        verify(ordineMapper).toEntity(dto);
        verify(ordineRepository).save(ordine);
        verify(ordineMapper).toDTO(ordine);
    }

    @Test
    void update_negative() {

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

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
    void delete_negative(){

        Integer id = 99;

        doThrow(new RuntimeException("Errore cancellazione"))
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

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

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

        when(ordineRepository.findByImporto(1000.0))
                .thenThrow(new RuntimeException("Database error"));

        assertThrows(
                RuntimeException.class,
                () -> ordineService.findByImporto(1000.0)
        );

        verify(ordineRepository)
                .findByImporto(1000.0);
    }

    @Test
    void findByQuantita_nullResult() {

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();

        when(ordineRepository.findByQuantita(10))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.findByQuantita(10);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(ordineRepository)
                .findByQuantita(10);

        verify(ordineMapper)
                .toDTOList(ordini);
    }

    @Test
    void findAll_positive() {

        Ordine ordine = ordine1;
        OrdineDto dto = ordineDto;

        List<Ordine> ordini = List.of(ordine);
        List<OrdineDto> dtoList = List.of(dto);

        when(ordineRepository.findAll())
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        Iterable<OrdineDto> result =
                ordineService.getAll();

        assertNotNull(result);

        List<OrdineDto> resultList = (List<OrdineDto>) result;

        assertEquals(dtoList, resultList);
        assertEquals(1, resultList.size());

        verify(ordineRepository)
                .findAll();

        verify(ordineMapper)
                .toDTOList(ordini);
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

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

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

        LocalDateTime data1 = data;

        when(ordineRepository.findByDataCreazione(data1))
                .thenReturn(null);

        when(ordineMapper.toDTO(null))
                .thenReturn(null);

        OrdineDto result = ordineService.findByDataCreazione(data1);

        assertNull(result);

        verify(ordineRepository).findByDataCreazione(data);
        verify(ordineMapper).toDTO(null);
    }

    @Test
    void findByImporto_positive() {

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;


        List<Ordine> ordini = List.of(ordine);
        List<OrdineDto> dtoList = List.of(dto);

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

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

        List<Ordine> ordini = List.of(ordine);
        List<OrdineDto> dtoList = List.of(dto);

        when(ordineRepository.findByQuantita(10))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.findByQuantita(10);

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

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();

        when(ordineRepository.findByQuantita(100))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.findByQuantita(100);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(ordineRepository)
                .findByQuantita(100);

        verify(ordineMapper)
                .toDTOList(ordini);
    }

    @Test
    void cercaProdotti_positive() {

        String prodotto = "Laptop";

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

        List<Ordine> ordini = List.of(ordine);
        List<OrdineDto> dtoList = List.of(dto);

        when(ordineRepository.cercaProdotti(prodotto))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.cercaProdotti(prodotto);

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

        String prodotto = "Tablet";

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();

        when(ordineRepository.cercaProdotti(prodotto))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.cercaProdotti(prodotto);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(ordineRepository)
                .cercaProdotti(prodotto);

        verify(ordineMapper)
                .toDTOList(ordini);
    }

    @Test
    void ordiniDalPiuCostoso_positive() {

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

        List<Ordine> ordini = List.of(ordine);
        List<OrdineDto> dtoList = List.of(dto);

        when(ordineRepository.ordiniDalPiuCostoso())
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.ordiniDalPiuCostoso();

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

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();

        when(ordineRepository.ordiniDalPiuCostoso())
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.ordiniDalPiuCostoso();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(ordineRepository)
                .ordiniDalPiuCostoso();

        verify(ordineMapper)
                .toDTOList(ordini);
    }

    @Test
    void cercaPerQuantitaMaggiore_positive() {

        int quantita = 5;

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

        List<Ordine> ordini = List.of(ordine);
        List<OrdineDto> dtoList = List.of(dto);

        when(ordineRepository.cercaPerQuantitaMaggiore(quantita))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.cercaPerQuantitaMaggiore(quantita);

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

        int quantita = 100;

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();

        when(ordineRepository.cercaPerQuantitaMaggiore(quantita))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.cercaPerQuantitaMaggiore(quantita);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(ordineRepository)
                .cercaPerQuantitaMaggiore(quantita);

        verify(ordineMapper)
                .toDTOList(ordini);
    }

    @Test
    void ultimiOrdini_positive() {

        Integer idUsers = 1;

        OrdineDto dto = ordineDto;
        Ordine ordine = ordine1;

        List<Ordine> ordini = List.of(ordine);

        List<OrdineDto> dtoList = List.of(dto);

        when(ordineRepository.ultimiOrdini(idUsers))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.ultimiOrdini(idUsers);

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

        Integer idUsers = 99;

        List<Ordine> ordini = new ArrayList<>();
        List<OrdineDto> dtoList = new ArrayList<>();

        when(ordineRepository.ultimiOrdini(idUsers))
                .thenReturn(ordini);

        when(ordineMapper.toDTOList(ordini))
                .thenReturn(dtoList);

        List<OrdineDto> result =
                ordineService.ultimiOrdini(idUsers);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(ordineRepository)
                .ultimiOrdini(idUsers);

        verify(ordineMapper)
                .toDTOList(ordini);
    }
}
