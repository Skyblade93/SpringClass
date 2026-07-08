package it.classe.SpringClass.Controller;

import it.classe.SpringClass.Dto.TaskDto;
import it.classe.SpringClass.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController extends AbstractController<TaskDto>{

    @Autowired
    private TaskService taskService;

    @GetMapping("/nome/{nomeTask}")
    public List<TaskDto> getTasksByNome(@PathVariable("nomeTask") String nomeTask){
        return taskService.findByTaskName(nomeTask);
    }

    @GetMapping("/cerca")
    public List<TaskDto> cercaPerDescrizione(String descrizione){
        return taskService.findByTaskNameContaining(descrizione);
    }

    @GetMapping("/completati")
    public List<TaskDto> getTaskCompletati(){
        return taskService.findByStatus(true);
    }

    @GetMapping("/urgenti")
    public List<TaskDto> getTaskUrgenti() {
        return taskService.findTaskUrgenti();
    }

    @GetMapping("/da-completare")
    public List<TaskDto> getTaskDaCompletare(){
        return taskService.findByStatus(false);
    }

}
