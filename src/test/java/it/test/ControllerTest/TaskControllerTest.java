package it.test.ControllerTest;

import it.classe.SpringClass.Controller.TaskController;
import it.classe.SpringClass.Dto.TaskDto;
import it.classe.SpringClass.Service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Collections;


@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;



    @Test
    void testGetTasksByNome_successo() {
        String nomeCercato = "Task2";

        TaskDto dtoFinto = new TaskDto();
        dtoFinto.setTaskName(nomeCercato);
        List<TaskDto> listaDtoFinta = List.of(dtoFinto);

        when(taskService.findByTaskName(nomeCercato)).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskController.getTasksByNome(nomeCercato);

        assertNotNull(risultato, "La lista non deve essere null");
        assertEquals(1, risultato.size(), "La lista deve contenere un solo elemento");
        assertEquals(nomeCercato, risultato.get(0).getTaskName());

        verify(taskService, times(1)).findByTaskName(nomeCercato);
    }

    @Test
    void testGetTasksByNome_nonSuccesso() {
        String nomeCercato = "Task Inesistente";

        when(taskService.findByTaskName(nomeCercato)).thenReturn(Collections.emptyList());

        List<TaskDto> risultato = taskController.getTasksByNome(nomeCercato);

        assertNotNull(risultato, "Il controller non deve restituire null, ma una lista vuota");
        assertTrue(risultato.isEmpty(), "La lista restituita deve essere vuota");

        verify(taskService, times(1)).findByTaskName(nomeCercato);
    }



    @Test
    void testCercaPerDescrizione_successo() {
        String descrizione = "urgente";

        TaskDto dtoFinto = new TaskDto();
        dtoFinto.setDescription(descrizione);
        List<TaskDto> listaDtoFinta = List.of(dtoFinto);

        when(taskService.findByTaskNameContaining(descrizione)).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskController.cercaPerDescrizione(descrizione);

        assertNotNull(risultato, "La lista non deve essere null");
        assertEquals(1, risultato.size(), "La lista deve contenere un solo elemento");

        verify(taskService, times(1)).findByTaskNameContaining(descrizione);
    }

    @Test
    void testCercaPerDescrizione_nonSuccesso() {
        String descrizione = "parolaInesistente";

        when(taskService.findByTaskNameContaining(descrizione)).thenReturn(Collections.emptyList());

        List<TaskDto> risultato = taskController.cercaPerDescrizione(descrizione);

        assertNotNull(risultato, "Il controller deve restituire una lista vuota, non null");
        assertTrue(risultato.isEmpty(), "La lista risultato deve essere vuota");

        verify(taskService, times(1)).findByTaskNameContaining(descrizione);
    }



    @Test
    void testGetTaskCompletati_successo() {
        TaskDto dtoCompletato = new TaskDto();
        dtoCompletato.setCompleted(true);
        List<TaskDto> listaDtoFinta = List.of(dtoCompletato);

        when(taskService.findByStatus(true)).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskController.getTaskCompletati();

        assertNotNull(risultato, "La lista non deve essere nulla");
        assertEquals(1, risultato.size());
        assertTrue(risultato.get(0).isCompleted(), "Il task deve risultare completato");

        verify(taskService, times(1)).findByStatus(true);
    }

    @Test
    void testGetTaskDaCompletare_successo() {
        TaskDto dtoDaCompletare = new TaskDto();
        dtoDaCompletare.setCompleted(false);
        List<TaskDto> listaDtoFinta = List.of(dtoDaCompletare);

        when(taskService.findByStatus(false)).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskController.getTaskDaCompletare();

        assertNotNull(risultato, "La lista non deve essere nulla");
        assertEquals(1, risultato.size());
        assertFalse(risultato.get(0).isCompleted(), "Il task non deve risultare completato");

        verify(taskService, times(1)).findByStatus(false);
    }

    @Test
    void testGetTaskUrgenti_successo() {
        TaskDto dtoUrgente = new TaskDto();
        dtoUrgente.setCompleted(false);
        dtoUrgente.setTaskName("Consegnare progetto");
        List<TaskDto> listaDtoFinta = List.of(dtoUrgente);

        when(taskService.findTaskUrgenti()).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskController.getTaskUrgenti();

        assertNotNull(risultato, "La lista risultato non deve essere nulla");
        assertEquals(1, risultato.size());
        assertFalse(risultato.get(0).isCompleted(), "I task urgenti non devono essere già completati");

        verify(taskService, times(1)).findTaskUrgenti();
    }

    @Test
    void testGetTaskUrgenti_nonSuccesso() {
        when(taskService.findTaskUrgenti()).thenReturn(Collections.emptyList());

        List<TaskDto> risultato = taskController.getTaskUrgenti();

        assertNotNull(risultato, "Il controller deve restituire una lista vuota, non null");
        assertTrue(risultato.isEmpty(), "La lista risultato deve essere vuota");

        verify(taskService, times(1)).findTaskUrgenti();
    }
}