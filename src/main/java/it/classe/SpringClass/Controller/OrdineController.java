package it.classe.SpringClass.Controller;




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
    public String url() {
        return ordineService.getDatabaseUrl();
    }


    @GetMapping("/data-creazione/{dataCreazione}")
    public OrdineDto findByDataCreazione(
            @PathVariable LocalDateTime dataCreazione) {

        return ordineService.findByDataCreazione(dataCreazione);
    }


    @GetMapping("/importo/{importo}")
    public List<OrdineDto> findByImporto(
            @PathVariable double importo) {

        return ordineService.findByImporto(importo);
    }


    @GetMapping("/quantita/{quantita}")
    public List<OrdineDto> findByQuantita(
            @PathVariable int quantita) {

        return ordineService.findByQuantita(quantita);
    }

    @GetMapping("/users/{idUsers}")
    public List<OrdineDto> findByUsers(@PathVariable Integer idUsers) {

        Users users = new Users();
        users.setId(idUsers);

        return ordineService.findByUsers(users);
    }


    @GetMapping("/prodotti/{prodotti}")
    public List<OrdineDto> cercaProdotti(
            @PathVariable String prodotti) {

        return ordineService.cercaProdotti(prodotti);
    }


    @GetMapping("/piu-costosi")
    public List<OrdineDto> ordiniDalPiuCostoso() {

        return ordineService.ordiniDalPiuCostoso();
    }


    @GetMapping("/quantita-maggiore/{quantita}")
    public List<OrdineDto> cercaPerQuantitaMaggiore(
            @PathVariable int quantita) {

        return ordineService.cercaPerQuantitaMaggiore(quantita);
    }


    @GetMapping("/ultimi/{idUsers}")
    public List<OrdineDto> ultimiOrdini(
            @PathVariable Integer idUsers) {

        return ordineService.ultimiOrdini(idUsers);
    }


}