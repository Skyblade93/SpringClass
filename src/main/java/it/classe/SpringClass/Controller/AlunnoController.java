package it.classe.SpringClass.Controller;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Service.AlunnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunno")
@CrossOrigin(origins = "http://localhost:4200")
public class AlunnoController extends AbstractController<AlunnoDto> {

    @Autowired
    private AlunnoService alunnoService;

    @GetMapping("/url")
    public String url() {
        return alunnoService.getDatabaseUrl();
    }

    @GetMapping("/nome/{nome}")
    public AlunnoDto findByNome(@PathVariable String nome) {
        return alunnoService.findByNome(nome);
    }

    @GetMapping("/cognome/{cognome}")
    public AlunnoDto findByCognome(@PathVariable String cognome) {
        return alunnoService.findByCognome(cognome);
    }
}