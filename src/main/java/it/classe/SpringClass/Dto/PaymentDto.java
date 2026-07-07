package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.PaymentMethod;
import it.classe.SpringClass.Model.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentDto {
    private Integer id;
    private PaymentStatus status;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private LocalDateTime processedAt;
    private UsersDto payer;
    private UsersDto payee;
}
