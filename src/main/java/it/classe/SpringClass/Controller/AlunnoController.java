package it.classe.SpringClass.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Service.AlunnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunno")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Alunno Controller", description = "ricerca alunno")
public class AlunnoController extends AbstractController<AlunnoDto> {

    @Autowired
    private AlunnoService alunnoService;

    @GetMapping("/url")
    @Operation(summary = "Ottieni URL del database", description = "stringa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "URL OK"),
            @ApiResponse(responseCode = "500", description = "So hazzi")
    })
    public String url() {
        return alunnoService.getDatabaseUrl();
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Trova alunno dal nome", description = "cerca nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunno trovato"),
            @ApiResponse(responseCode = "400", description = "Nome non valido"),
            @ApiResponse(responseCode = "404", description = "Nessun alunno trovato con il nome inserito, scrivi meglio C."),
            @ApiResponse(responseCode = "500", description = "Errore")
    })
    public AlunnoDto findByNome(@PathVariable String nome) {
        return alunnoService.findByNome(nome);
    }

    @GetMapping("/cognome/{cognome}")
    @Operation(summary = "Trova alunno dal cognome", description = " Cerca cognome ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunno trovato "),
            @ApiResponse(responseCode = "400", description = "Cognome non valido"),
            @ApiResponse(responseCode = "404", description = "cognome sbagliato, sicuramete lo cerca il padre"),
            @ApiResponse(responseCode = "500", description = "Errore  server")
    })
    public AlunnoDto findByCognome(@PathVariable String cognome) {
        return alunnoService.findByCognome(cognome);
    }
}