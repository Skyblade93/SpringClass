package it.classe.SpringClass.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CardsController extends AbstractController<CardsDto> {


    private final CardsService cardsService;

    @GetMapping("/telefono")
    @Operation(summary = "Cerca per telefono", description = "Recupera i dettagli di una carta tramite il numero di telefono associato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carta trovata con successo",
                    content = @Content(schema = @Schema(implementation = CardsDto.class)))
    })
    public CardsDto getByCell(@RequestParam("telefono") String telefono) {
        return cardsService.findByTelefono(telefono);
    }

    @GetMapping("/type")
    @Operation(summary = "Cerca per tipo card",
                description = "Ritoran una lista di card in base al cardType")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Succ",
            content = @Content(schema = @Schema(implementation = CardsDto.class)))
    })
    public List<CardsDto> getByCartType(@RequestParam("cartType") String cardType) {
        return cardsService.findByCartType(cardType);
    }

    @Operation(summary = "Cerca per cardNumber", description = "Recupera i dettagli di una card tramite il cardNumber.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carta trovata con successo",
                    content = @Content(schema = @Schema(implementation = CardsDto.class)))
    })
    @GetMapping("/number")
    public CardsDto getByCardNumber(@RequestParam("cardNumber") String cardNumber) {
        return cardsService.findByCardNumber(cardNumber);
    }

    @GetMapping("/amount")
    @Operation(summary = "Filtra per amount", description = "Recupera card che corrisponde al ammount.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succ")
    })
    public List<CardsDto> getByAmount(@RequestParam("amount") int amount) {
        return cardsService.findByAmount(amount);
    }

    @GetMapping("/available-amount")
    @Operation(summary = "Filtra per saldo disponibile", description = "Ritorna le card che hanno amount disponibile specificato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succ")
    })
    public List<CardsDto> getByAvailableAmount(@RequestParam("availableAmount") int availableAmount) {
        return cardsService.findByAvailableAmount(availableAmount);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Aggiorna telefono", description = "Modifica il numero di telefono associato alla card con un id dato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefono aggiornato correttamente",
                    content = @Content(schema = @Schema(implementation = CardsDto.class))),
            @ApiResponse(responseCode = "500", description = "Errore")
    })
    public ResponseEntity<CardsDto> updateTelefono(
            @PathVariable(name = "id") Integer id,
            @RequestParam("telefono") String telefono) throws Exception {
        return ResponseEntity.ok(cardsService.changeTelefono(id, telefono));
    }
}
