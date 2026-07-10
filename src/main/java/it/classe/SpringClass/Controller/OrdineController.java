package it.classe.SpringClass.Controller;




import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/ordine")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdineController extends AbstractController<OrdineDto> {


    @Autowired
    private OrdineService ordineService;


    @GetMapping("/url")
    @Operation(
            summary = "Recupera URL database",
            description = "Restituisce l'URL del database utilizzato dall'applicazione"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "URL recuperato correttamente"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public String url() {
        return ordineService.getDatabaseUrl();
    }


    @GetMapping("/data-creazione/{dataCreazione}")
    @Operation(
            summary = "Trova ordine per data di creazione",
            description = "Permette di ottenere un ordine tramite la sua data di creazione"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine trovato correttamente"),
            @ApiResponse(responseCode = "400", description = "Data non valida"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public OrdineDto findByDataCreazione(
            @PathVariable LocalDateTime dataCreazione) {

        return ordineService.findByDataCreazione(dataCreazione);
    }


    @GetMapping("/importo/{importo}")
    @Operation(
            summary = "Trova ordini per importo",
            description = "Restituisce tutti gli ordini aventi l'importo indicato"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordini recuperata correttamente"),
            @ApiResponse(responseCode = "400", description = "Importo non valido"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<OrdineDto> findByImporto(
            @PathVariable double importo) {

        return ordineService.findByImporto(importo);
    }


    @GetMapping("/quantita/{quantita}")
    @Operation(
            summary = "Trova ordini per quantità",
            description = "Restituisce gli ordini che hanno la quantità indicata"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordini recuperata correttamente"),
            @ApiResponse(responseCode = "400", description = "Quantità non valida"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<OrdineDto> findByQuantita(
            @PathVariable int quantita) {

        return ordineService.findByQuantita(quantita);
    }


    @GetMapping("/users/{idUsers}")
    @Operation(
            summary = "Trova ordini di un utente",
            description = "Restituisce tutti gli ordini associati ad uno specifico utente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordini dell'utente recuperati correttamente"),
            @ApiResponse(responseCode = "400", description = "ID utente non valido"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<OrdineDto> findByUsers(@PathVariable Integer idUsers) {

        Users users = new Users();
        users.setId(idUsers);

        return ordineService.findByUsers(users);
    }


    @GetMapping("/prodotti/{prodotti}")
    @Operation(
            summary = "Cerca ordini per prodotto",
            description = "Restituisce gli ordini contenenti il prodotto indicato"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ricerca completata con successo"),
            @ApiResponse(responseCode = "400", description = "Nome prodotto non valido"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<OrdineDto> cercaProdotti(
            @PathVariable String prodotti) {

        return ordineService.cercaProdotti(prodotti);
    }


    @GetMapping("/piu-costosi")
    @Operation(
            summary = "Ordina gli ordini più costosi",
            description = "Restituisce la lista degli ordini ordinati dal più costoso al meno costoso"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordinata correttamente"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<OrdineDto> ordiniDalPiuCostoso() {

        return ordineService.ordiniDalPiuCostoso();
    }


    @GetMapping("/quantita-maggiore/{quantita}")
    @Operation(
            summary = "Trova ordini con quantità maggiore",
            description = "Restituisce gli ordini con quantità superiore al valore indicato"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ricerca completata con successo"),
            @ApiResponse(responseCode = "400", description = "Quantità non valida"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<OrdineDto> cercaPerQuantitaMaggiore(
            @PathVariable int quantita) {

        return ordineService.cercaPerQuantitaMaggiore(quantita);
    }


    @GetMapping("/ultimi/{idUsers}")
    @Operation(
            summary = "Recupera ultimi ordini utente",
            description = "Restituisce gli ultimi ordini effettuati da uno specifico utente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ultimi ordini recuperati correttamente"),
            @ApiResponse(responseCode = "400", description = "ID utente non valido"),
            @ApiResponse(responseCode = "500", description = "Errore interno del server")
    })
    public List<OrdineDto> ultimiOrdini(
            @PathVariable Integer idUsers) {

        return ordineService.ultimiOrdini(idUsers);
    }

}