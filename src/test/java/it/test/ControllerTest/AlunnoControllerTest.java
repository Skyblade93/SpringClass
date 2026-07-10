package it.test.ControllerTest;

import it.classe.SpringClass.Controller.AlunnoController;
import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Model.Scuola;
import it.classe.SpringClass.Service.AlunnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlunnoControllerTest {

    @Mock
    private AlunnoService alunnoService;

    @InjectMocks
    private AlunnoController alunnoController;

    @Test
    void url_ok() {

        when(alunnoService.getDatabaseUrl())
                .thenReturn("jdbc:postgresql://localhost:5432/asset");

        String actual = alunnoController.url();

        assertEquals("jdbc:postgresql://localhost:5432/asset", actual);

        verify(alunnoService, times(1)).getDatabaseUrl();
        verifyNoMoreInteractions(alunnoService);
    }

    @Test
    void findByNome_ok() {

        AlunnoDto dto = new AlunnoDto(
                1,
                "Mario",
                "Rossi",
                8,
                Scuola.CLASSICO,
                List.of(),
                List.of()
        );

        when(alunnoService.findByNome("Mario")).thenReturn(dto);

        AlunnoDto actual = alunnoController.findByNome("Mario");

        assertEquals(dto, actual);

        verify(alunnoService, times(1)).findByNome("Mario");
        verifyNoMoreInteractions(alunnoService);
    }

    @Test
    void findByNome_ko() {

        when(alunnoService.findByNome("Pluto")).thenReturn(null);

        AlunnoDto actual = alunnoController.findByNome("Pluto");

        assertNull(actual);

        verify(alunnoService, times(1)).findByNome("Pluto");
        verifyNoMoreInteractions(alunnoService);
    }
}