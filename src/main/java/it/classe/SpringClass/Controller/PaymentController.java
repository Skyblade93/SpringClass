package it.classe.SpringClass.Controller;

import it.classe.SpringClass.Dto.PaymentDto;
import it.classe.SpringClass.Model.PaymentStatus;
import it.classe.SpringClass.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/payments")
@RestController
public class PaymentController extends AbstractController<PaymentDto>{

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payer/{payerId}")
    public List<PaymentDto> getPaymentsByPayer(
            @PathVariable Integer payerId){

        return paymentService.getPaymentsByPayer(payerId);
    }

    @GetMapping("/payee/{payeeId}")
    public List<PaymentDto> getPaymentsByPayee(
            @PathVariable Integer payeeId){

        return paymentService.getPaymentsByPayee(payeeId);
    }

    @GetMapping("/username/{username}")
    public List<PaymentDto> getPaymentsByUsername(
            @PathVariable String username){

        return paymentService.getPaymentsByUsername(username);
    }

    @GetMapping("/filter")
    public List<PaymentDto> getPaymentsByStatusAndMinAmount(
            @RequestParam PaymentStatus status,
            @RequestParam BigDecimal amount){

        return paymentService.getPaymentsByStatusAndMinAmount(
                status,
                amount
        );
    }

    @GetMapping("/spent/{userId}")
    public BigDecimal getTotalSpentByUser(
            @PathVariable Integer userId){

        return paymentService.getTotalSpentByUser(userId);
    }

    @GetMapping("/received/{userId}")
    public BigDecimal getTotalReceivedByUser(
            @PathVariable Integer userId){

        return paymentService.getTotalReceivedByUser(userId);
    }
}
