package it.classe.SpringClass.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
    @Operation(summary = "Trova task dal nome", description = "Ti permette di ottenere una task cercandola tramite il suo nome esatto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ricerca completata con successo"),
            @ApiResponse(responseCode = "400", description = "Nome della task non valido o mancante"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<TaskDto> getTasksByNome(@PathVariable("nomeTask") String nomeTask){
        return taskService.findByTaskName(nomeTask);
    }

    @GetMapping("/cerca")
    @Operation(summary = "Trova task dalla descrizione",description = "Ti permette di ottenere una task tramite il suo contenuto testuale")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ricerca completata con successo"),
            @ApiResponse(responseCode = "400", description = "Parametro di descrizione non valido"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<TaskDto> cercaPerDescrizione(@RequestParam("descrizione") String descrizione){
        // Ora chiama il metodo corretto!
        return taskService.findByDescription(descrizione);
    }

    @GetMapping("/completati")
    @Operation(summary = "Trova task completate", description = "Ti restituisce tutte le task che risultano completate dall'utente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista delle task completate recuperata con successo"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<TaskDto> getTaskCompletati(){
        return taskService.findByStatus(true);
    }

    @GetMapping("/urgenti")
    @Operation(summary = "Trova task urgenti", description = "Ti restituisce tutte le task che risultano urgenti, ovvero quelle con la scadenza più prossima")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista delle task urgenti recuperata con successo"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<TaskDto> getTaskUrgenti() {
        return taskService.findTaskUrgenti();
    }

    @GetMapping("/da-completare")
    @Operation(summary = "Trova task incomplete", description = "Ti restituisce tutte le task che non sono state ancora completate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista delle task incomplete recuperata con successo"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<TaskDto> getTaskDaCompletare(){
        return taskService.findByStatus(false);
    }

}
