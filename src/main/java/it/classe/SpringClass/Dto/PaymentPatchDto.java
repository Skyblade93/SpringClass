package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.PaymentMethod;
import it.classe.SpringClass.Model.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPatchDto {

    private PaymentStatus status;

    private PaymentMethod paymentMethod;

    private BigDecimal amount;

    private LocalDateTime processedAt;
}