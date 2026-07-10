package it.classe.SpringClass.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Service.ContoCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contoCorrente")
@CrossOrigin(origins = "http://localhost:4200")
public class ContoCorrenteController extends AbstractController<ContoCorrenteDto>{

            @Autowired
            private ContoCorrenteService contoCorrenteService;


            @Operation(summary = "recupera l'url di connessione al nostro DB",
                    description ="Metodo che restituisce l'url della nostra connessione al Database" )
            @ApiResponses({
                @ApiResponse(responseCode="200",description="Url di ritono avvenuto con successo ! "),
                    @ApiResponse(responseCode="500",description="Url non trovato, errore interno del server ! ")
            })
            @GetMapping("/url")
            public String url(){
                return contoCorrenteService.getDatabaseUrl();
            }


            @Operation(summary = "Recupera il conto tramite email",
                    description ="Metodo che recupera il conto prendendo come parametro l'email" )
            @ApiResponses({
                    @ApiResponse(responseCode="200",description="Metodo che ritorna  un conto corrente tramite l'email"),
            @ApiResponse(responseCode="500",description="Errore generico , per il recupero dell'utente tramite email ! ")
             })
            @GetMapping("/email/{email}")
            public ContoCorrenteDto getContoByEmail(@PathVariable String email)
              {
                  return contoCorrenteService.findByEmail(email);
              }


             @Operation(summary = "Recupera il conto tramite il cognome",
            description ="Metodo che recupera il conto prendendo come parametro il cognome" )
            @ApiResponses({
            @ApiResponse(responseCode="200",description="Metodo che ritorna  un conto corrente tramite il cognome"),
            @ApiResponse(responseCode="500",description="Errore generico , per il recupero dell'utente tramite cognome ! ")
            })
            @GetMapping("/cognome/{cognome}")
            public ContoCorrenteDto findByCognome(@PathVariable String  cognome){
                          return contoCorrenteService.findByCognome(cognome);

                      }
}
