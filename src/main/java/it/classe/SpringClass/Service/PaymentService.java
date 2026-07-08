package it.classe.SpringClass.Service;

import it.classe.SpringClass.Dto.PaymentDto;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Mapper.PaymentMapper;
import it.classe.SpringClass.Model.Payment;
import it.classe.SpringClass.Model.PaymentStatus;
import it.classe.SpringClass.Repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService extends AbstractService<Payment, PaymentDto> {

    private final PaymentMapper paymentMapper;

    private final PaymentRepository paymentRepository;

    public PaymentService(JpaRepository<Payment, Integer> repository, Converter<Payment, PaymentDto> converter, PaymentMapper paymentMapper, PaymentRepository paymentRepository){
        super(repository, converter);
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentDto> getPaymentsByPayer(Integer payerId){
        return paymentMapper.toDTOList(
                paymentRepository.findByPayerId(payerId)
        );
    }

    public List<PaymentDto> getPaymentsByPayee(Integer payeeId){
        return paymentMapper.toDTOList(
                paymentRepository.findByPayeeId(payeeId)
        );
    }

    public List<PaymentDto> getPaymentsByUsername(String username){
        return paymentMapper.toDTOList(
                paymentRepository.findPaymentsByPayerUsername(username)
        );
    }

    public List<PaymentDto> getPaymentsByStatusAndMinAmount(
            PaymentStatus status,
            BigDecimal amount
    ){
        return paymentMapper.toDTOList(
                paymentRepository.findByStatusAndMinAmount(status, amount)
        );
    }

    public BigDecimal getTotalSpentByUser(Integer userId){
        return paymentRepository.getTotalSpentByUser(userId);
    }

    public BigDecimal getTotalReceivedByUser(Integer userId){
        return paymentRepository.getTotalReceivedByUser(userId);
    }

    public PaymentDto patch(
            Integer id,
            PaymentStatus status,
            BigDecimal amount){

        Payment payment = paymentRepository.findById(id)
                .orElseThrow();

        if(status != null){
            payment.setStatus(status);
        }

        if(amount != null){
            payment.setAmount(amount);
        }

        payment = paymentRepository.save(payment);
        return paymentMapper.toDTO(payment);
    }
}
