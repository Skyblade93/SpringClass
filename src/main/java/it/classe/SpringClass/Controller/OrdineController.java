package it.classe.SpringClass.Controller;




import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

}
