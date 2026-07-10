package it.test.ServiceTest;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Mapper.AlunniMapper;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Scuola;
import it.classe.SpringClass.Repository.AlunnoRepository;
import it.classe.SpringClass.Service.AlunnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlunnoServiceTest {

    @Mock
    private AlunnoRepository alunnoRepository;

    @Mock
    private JpaRepository<Alunno, Integer> repository;

    @Mock
    private AlunniMapper alunnoMapper;

    @InjectMocks
    private AlunnoService alunnoService;

    @Test
    //t'appost
    void findAll_ok() {
        Alunno alunno = new Alunno(
                1,
                "Mario",
                "Rossi",
                8,
                Scuola.CLASSICO,
                List.of()
        );

        AlunnoDto dto = new AlunnoDto(
                1,
                "Mario",
                "Rossi",
                8,
                Scuola.CLASSICO,
                List.of(),
                List.of()
        );

        List<Alunno> alunni = List.of(alunno);
        List<AlunnoDto> expected = List.of(dto);

        when(repository.findAll()).thenReturn(alunni);
        when(alunnoMapper.toDTOList(alunni)).thenReturn(expected);

        List<AlunnoDto> actual = new ArrayList<>();
        alunnoService.getAll().forEach(actual::add);

        assertEquals(expected, actual);

        verify(repository, times(1)).findAll();
        verify(alunnoMapper, times(1)).toDTOList(alunni);
    }

    @Test
        //Caso negativo di fallimento
    void findAll_ko_emptyList() {
        List<Alunno> alunni = List.of();
        List<AlunnoDto> expected = List.of();

        when(repository.findAll()).thenReturn(alunni);
        when(alunnoMapper.toDTOList(alunni)).thenReturn(expected);

        List<AlunnoDto> actual = new ArrayList<>();
        alunnoService.getAll().forEach(actual::add);

        assertTrue(actual.isEmpty());
        assertEquals(expected, actual);

        verify(repository, times(1)).findAll();
        verify(alunnoMapper, times(1)).toDTOList(alunni);
    }

    @Test
        //t'appost
    void findByNome_ok() {
        Alunno alunno = new Alunno(
                1,
                "Mario",
                "Rossi",
                8,
                Scuola.CLASSICO,
                List.of()
        );

        AlunnoDto dto = new AlunnoDto(
                1,
                "Mario",
                "Rossi",
                8,
                Scuola.CLASSICO,
                List.of(),
                List.of()
        );

        when(alunnoRepository.findByNome("Mario")).thenReturn(alunno);
        when(alunnoMapper.toDTO(alunno)).thenReturn(dto);

        AlunnoDto actual = alunnoService.findByNome("Mario");

        assertNotNull(actual);
        assertEquals("Mario", actual.getNome());
        assertEquals("Rossi", actual.getCognome());
        assertEquals(8, actual.getVoto());
        assertEquals(Scuola.CLASSICO, actual.getScuola());

        verify(alunnoRepository, times(1)).findByNome("Mario");
        verify(alunnoMapper, times(1)).toDTO(alunno);
    }

    @Test
    //Caso negativo di fallimento
    void findByNome_ko_notFound() {
        when(alunnoRepository.findByNome("Pluto")).thenReturn(null);
        when(alunnoMapper.toDTO(null)).thenReturn(null);

        AlunnoDto actual = alunnoService.findByNome("Pluto");

        assertNull(actual);

        verify(alunnoRepository, times(1)).findByNome("Pluto");
        verify(alunnoMapper, times(1)).toDTO(null);
    }

    //TEST PER COGNOME
    @Test
    void findByCognome_ok() {

        Alunno alunno = new Alunno(
                1,
                "Peppe",
                "Sbrescia",
                8,
                Scuola.PSICO,
                List.of()
        );

        AlunnoDto dto = new AlunnoDto(
                1,
                "Peppe",
                "Sbrescia",
                8,
                Scuola.PSICO,
                List.of(),
                List.of()
        );

        when(alunnoRepository.findByCognome("Sbrescia")).thenReturn(alunno);
        when(alunnoMapper.toDTO(alunno)).thenReturn(dto);

        AlunnoDto actual = alunnoService.findByCognome("Sbrescia");

        assertNotNull(actual);
        assertEquals("Sbrescia", actual.getCognome());

        verify(alunnoRepository, times(1)).findByCognome("Sbrescia");
        verify(alunnoMapper, times(1)).toDTO(alunno);
    }

//TEST COGNOME FALLITO
@Test
void findByCognome_fallito() {

    when(alunnoRepository.findByCognome("Berlusconi")).thenReturn(null);
    when(alunnoMapper.toDTO(null)).thenReturn(null);

    AlunnoDto actual = alunnoService.findByCognome("Berlusconi");

    assertNull(actual);

    verify(alunnoRepository, times(1)).findByCognome("Berlusconi");
    verify(alunnoMapper, times(1)).toDTO(null);
}}