package it.classe.SpringClass.Controller;



import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController extends AbstractController<UsersDto> {

    @Autowired
    private UsersService usersService;


    @GetMapping("/url")
    public String url() {
        return usersService.getDatabaseUrl();
    }

}


