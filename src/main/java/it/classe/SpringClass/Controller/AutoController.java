package it.classe.SpringClass.Controller;


import it.classe.SpringClass.Dto.AutoDto;
import it.classe.SpringClass.Service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auto")
@CrossOrigin(origins="http://localhost:4200")
public class AutoController extends AbstractController <AutoDto> {
    @Autowired
    private  AutoService autoService;

}
