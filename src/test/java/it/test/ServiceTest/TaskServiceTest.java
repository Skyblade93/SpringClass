package it.test.ServiceTest;

import it.classe.SpringClass.Dto.TaskDto;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Mapper.TaskMapper;
import it.classe.SpringClass.Model.Task;
import it.classe.SpringClass.Repository.TaskRepository;
import it.classe.SpringClass.Service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {


    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @Mock
    private JpaRepository<Task,Integer> jpaRepository;
    @Mock
    private Converter<Task,TaskDto> converter;


    @InjectMocks
    private TaskService taskService;

    @Test
    void testFindTaskByName_successo() {
        String nomeTask = "Task2";
        Task task = new Task();
        task.setTaskName(nomeTask);
        List<Task> listaTaskFinta = List.of(task);

        TaskDto taskDto = new TaskDto();
        taskDto.setTaskName(nomeTask);
        List<TaskDto> listaDtoFinta = List.of(taskDto);

        when(taskRepository.findByTaskName(nomeTask)).thenReturn(listaTaskFinta);
        when(taskMapper.toDTOList(listaTaskFinta)).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskService.findByTaskName(nomeTask);

        assertNotNull(risultato, "La lista non deve essere null");
        assertEquals(1,risultato.size(),"La lista deve contenere un solo elemento");
        assertEquals(nomeTask,risultato.get(0).getTaskName());

        //verifico
        verify(taskRepository,times(1)).findByTaskName(nomeTask);
        verify(taskMapper,times(1)).toDTOList(listaTaskFinta);

    }

    @Test
    void testFindByTaskName_NessunRisultato() {
        String nomeCercato = "Task Inesistente";

        when(taskRepository.findByTaskName(nomeCercato)).thenReturn(Collections.emptyList());
        when(taskMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<TaskDto> risultato = taskService.findByTaskName(nomeCercato);
        assertNotNull(risultato, "Il service non deve restituire null, ma una lista vuota");
        assertTrue(risultato.isEmpty(), "La lista restituita deve essere vuota");
        verify(taskRepository, times(1)).findByTaskName(nomeCercato);
    }

    @Test
    void testFindByStatus_successo(){
        boolean statoCercato = true;
        Task task = new Task();
        task.setCompleted(statoCercato);
        List<Task> listFintaTaskCompletate = List.of(task);

        TaskDto taskDto = new TaskDto();
        taskDto.setCompleted(statoCercato);
        List<TaskDto> listFintaTaskDtoCompletate = List.of(taskDto);

        when(taskRepository.findByCompleted(statoCercato)).thenReturn(listFintaTaskCompletate);
        when(taskMapper.toDTOList(listFintaTaskCompletate)).thenReturn(listFintaTaskDtoCompletate);

        List<TaskDto> risultato = taskService.findByStatus(statoCercato);

        assertNotNull(risultato);
        assertEquals(1, risultato.size());

        verify(taskRepository, times(1)).findByCompleted(statoCercato);
        verify(taskMapper, times(1)).toDTOList(listFintaTaskCompletate);
    }

    @Test
    void testFindByStatus_DaCompletare() {
        boolean statoCercato = false;

        Task taskDaCompletare = new Task();
        taskDaCompletare.setCompleted(statoCercato);
        List<Task> listaTaskFinta = List.of(taskDaCompletare);

        TaskDto dtoDaCompletare = new TaskDto();
        dtoDaCompletare.setCompleted(statoCercato);
        List<TaskDto> listaDtoFinta = List.of(dtoDaCompletare);

        when(taskRepository.findByCompleted(statoCercato)).thenReturn(listaTaskFinta);
        when(taskMapper.toDTOList(listaTaskFinta)).thenReturn(listaDtoFinta);

        List<TaskDto> risultato = taskService.findByStatus(statoCercato);

        assertNotNull(risultato);
        assertEquals(1, risultato.size());

        verify(taskRepository, times(1)).findByCompleted(statoCercato);
        verify(taskMapper, times(1)).toDTOList(listaTaskFinta);
    }

    @Test
    void testFindByDescription_successo() {
        String descrizione = "descrizione1";

        Task task = new Task();
        task.setDescription(descrizione);
        List<Task> ListaTaskFinta = List.of(task);

        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(descrizione);
        List<TaskDto> ListaTaskDto = List.of(taskDto);

        when(taskRepository.findByDescriptionContainingIgnoreCase(descrizione)).thenReturn(ListaTaskFinta);
        when(taskMapper.toDTOList(ListaTaskFinta)).thenReturn(ListaTaskDto);


        List<TaskDto> risultato = taskService.findByDescription(descrizione);

        assertNotNull(risultato, "La lista risultato non deve essere nulla");
        assertEquals(1, risultato.size(), "La lista deve contenere esattamente 1 elemento");
        assertEquals(descrizione, risultato.get(0).getDescription(), "La descrizione deve corrispondere");

        verify(taskRepository).findByDescriptionContainingIgnoreCase(descrizione);
        verify(taskMapper).toDTOList(ListaTaskFinta);
    }

    @Test
    void testFindByDescription_nonSuccesso() {
        String descrizione = "descrizioneInesistente";

        when(taskRepository.findByDescriptionContainingIgnoreCase(descrizione)).thenReturn(Collections.emptyList());
        when(taskMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());


        // Usiamo il nuovo metodo del Service
        List<TaskDto> risultato = taskService.findByDescription(descrizione);


        assertNotNull(risultato, "Il service deve restituire una lista vuota, non null");
        assertTrue(risultato.isEmpty(), "La lista risultato deve essere vuota");


        verify(taskRepository).findByDescriptionContainingIgnoreCase(descrizione);
        verify(taskMapper).toDTOList(Collections.emptyList());
    }

    @Test
    void testFindTaskUrgenti_successo() {
        //task fittizia con alta priorita
        Task taskUrgente = new Task();
        taskUrgente.setCompleted(false);
        taskUrgente.setTaskName("Consegnare progetto");
        List<Task> listaTaskFinta = List.of(taskUrgente);

        TaskDto dtoUrgente = new TaskDto();
        dtoUrgente.setCompleted(false);
        dtoUrgente.setTaskName("Consegnare progetto");
        List<TaskDto> listaDtoFinta = List.of(dtoUrgente);

        when(taskRepository.findByCompletedFalseOrderByDataScadenzaAsc()).thenReturn(listaTaskFinta);
        when(taskMapper.toDTOList(listaTaskFinta)).thenReturn(listaDtoFinta);
        List<TaskDto> risultato = taskService.findTaskUrgenti();

        assertNotNull(risultato, "La lista risultato non deve essere nulla");
        assertEquals(1, risultato.size(), "La lista deve contenere esattamente 1 elemento");
        assertFalse(risultato.get(0).isCompleted(), "Il task non deve essere completato");

        verify(taskRepository, times(1)).findByCompletedFalseOrderByDataScadenzaAsc();
        verify(taskMapper, times(1)).toDTOList(anyList());    }

    @Test
    void testFindTaskUrgenti_nonSuccesso() {
        when(taskRepository.findByCompletedFalseOrderByDataScadenzaAsc()).thenReturn(Collections.emptyList());
        when(taskMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<TaskDto> risultato = taskService.findTaskUrgenti();

        assertNotNull(risultato, "Il service deve restituire una lista vuota, non null");
        assertTrue(risultato.isEmpty(), "La lista risultato deve essere vuota");

        verify(taskRepository, times(1)).findByCompletedFalseOrderByDataScadenzaAsc();
        verify(taskMapper, times(1)).toDTOList(Collections.emptyList());
    }


}
