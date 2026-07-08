package it.classe.SpringClass.Controller;

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

            @GetMapping("/url")
            public String url(){
                return contoCorrenteService.getDatabaseUrl();
            }


            @GetMapping("/email/{email}")
            public ContoCorrenteDto getContoByEmail(@PathVariable String email)
              {
                  return contoCorrenteService.findByEmail(email);
              }
}
