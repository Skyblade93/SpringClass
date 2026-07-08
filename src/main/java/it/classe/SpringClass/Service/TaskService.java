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

    public List<TaskDto> findByName(String nome){
        return taskMapper.toDTOList(taskRepository.findByTaskName(nome));
    }

    public String getDatabaseUrl() {
        log.info(databaseUrl);
        log.warn(databaseUrl);
        log.error(databaseUrl);
        return databaseUrl;
    }
}

