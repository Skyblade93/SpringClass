package it.classe.SpringClass.Service;

import it.classe.SpringClass.Dto.TaskDto;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Mapper.TaskMapper;
import it.classe.SpringClass.Model.Task;
import it.classe.SpringClass.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends AbstractService<Task,TaskDto>{

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    //@Value("${spring.datasource.url}")
    private String databaseUrl;

    public TaskService(JpaRepository<Task,Integer> repository,
                       Converter<Task,TaskDto> converter,
                       TaskMapper taskMapper,
                       TaskRepository taskRepository)
    {
        super(repository,converter);
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> findByTaskName(String taskName) {
        return taskMapper.toDTOList(taskRepository.findByTaskName(taskName));
    }

    public List<TaskDto> findByTaskNameContaining(String keyword) {
        return taskMapper.toDTOList(taskRepository.findByTaskNameContaining(keyword));
    }


    public List<TaskDto> findTaskUrgenti() {
        return taskMapper.toDTOList(taskRepository.findByCompletedFalseOrderByDataScadenzaAsc());
    }

    public List<TaskDto> findByStatus(boolean status) {
        return taskMapper.toDTOList(taskRepository.findByCompleted(status));
    }

    public List<TaskDto> trovaPerNomeEStato(String nome, boolean stato) {
        return taskMapper.toDTOList(taskRepository.trovaPerNomeEStato(nome, stato));
    }

    public List<TaskDto> cercaPerDescrizioneJPQL(String descrizione) {
        return taskMapper.toDTOList(taskRepository.cercaPerDescrizioneJPQL(descrizione));
    }


    public List<TaskDto> trovaTaskCompletate() {
        return taskMapper.toDTOList(taskRepository.trovaTaskCompletate());
    }

    public List<TaskDto> trovaTaskNonCompletate() {
        return taskMapper.toDTOList(taskRepository.trovaTaskNonCompletate());
    }





    public String getDatabaseUrl() {
        log.info(databaseUrl);
        log.warn(databaseUrl);
        log.error(databaseUrl);
        return databaseUrl;
    }
}

