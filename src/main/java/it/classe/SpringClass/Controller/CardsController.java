package it.classe.SpringClass.Controller;

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
    public CardsDto getByCell(@RequestParam("telefono") String telefono) {
        return cardsService.findByTelefono(telefono);
    }

    @GetMapping("/type")
    public List<CardsDto> getByCartType(@RequestParam("cartType") String cardType) {
        return cardsService.findByCartType(cardType);
    }

    @GetMapping("/number")
    public CardsDto getByCardNumber(@RequestParam("cardNumber") String cardNumber) {
        return cardsService.findByCardNumber(cardNumber);
    }

    @GetMapping("/amount")
    public List<CardsDto> getByAmount(@RequestParam("amount") int amount) {
        return cardsService.findByAmount(amount);
    }

    @GetMapping("/available-amount")
    public List<CardsDto> getByAvailableAmount(@RequestParam("availableAmount") int availableAmount) {
        return cardsService.findByAvailableAmount(availableAmount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardsDto> updateTelefono(
            @PathVariable(name = "id") Integer id,
            @RequestParam("telefono") String telefono) throws Exception {
        return ResponseEntity.ok(cardsService.changeTelefono(id, telefono));
    }
}
