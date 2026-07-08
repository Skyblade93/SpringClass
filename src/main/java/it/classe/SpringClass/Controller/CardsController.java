package it.classe.SpringClass.Controller;

import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CardsController extends AbstractController<CardsDto> {


    private final CardsService cardsService;

    @GetMapping("/telefono")
    public CardsDto getByCell(String telefono) {
        return cardsService.findByTelefono(telefono);
    }

    @GetMapping("/type")
    public List<CardsDto> getByCartType(String cartType) {
        return cardsService.findByCartType(cartType);
    }

    @GetMapping("/number")
    public CardsDto getByCardNumber(String cardNumber) {
        return cardsService.findByCardNumber(cardNumber);
    }

    @GetMapping("/amount")
    public List<CardsDto> getByAmount(int amount) {
        return cardsService.findByAmount(amount);
    }

    @GetMapping("/available-amount")
    public List<CardsDto> getByAvailableAmount(int availableAmount) {
        return cardsService.findByAvailableAmount(availableAmount);
    }
}
