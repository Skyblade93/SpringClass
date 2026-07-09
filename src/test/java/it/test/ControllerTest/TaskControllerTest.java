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

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;


    @Test
    void testGetTasksByNome_Successo() {
        // ARRANGIA
        String nomeCercato = "Studiare i Controller";
        TaskDto dtoFinto = new TaskDto();
        dtoFinto.setTaskName(nomeCercato);
        List<TaskDto> listaDtoFinta = List.of(dtoFinto);

        when(taskService.findByTaskName(nomeCercato)).thenReturn(listaDtoFinta);
        List<TaskDto> risultato = taskController.getTasksByNome(nomeCercato);


        assertNotNull(risultato);
        assertEquals(1, risultato.size());
        assertEquals(nomeCercato, risultato.get(0).getTaskName());

        verify(taskService, times(1)).findByTaskName(nomeCercato);
    }


    @Test
    void testGetTaskCompletati_Successo() {
        // ARRANGIA
        TaskDto dtoCompletato = new TaskDto();
        dtoCompletato.setCompleted(true);
        List<TaskDto> listaDtoFinta = List.of(dtoCompletato);

        when(taskService.findByStatus(true)).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskController.getTaskCompletati();

        assertNotNull(risultato);
        assertEquals(1, risultato.size());
        assertTrue(risultato.get(0).isCompleted());

        verify(taskService, times(1)).findByStatus(true);
    }
}