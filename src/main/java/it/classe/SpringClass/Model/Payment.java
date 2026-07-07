package it.classe.SpringClass.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments",schema = "class")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private PaymentStatus status;

    private PaymentMethod paymentMethod;

    private BigDecimal amount;

    private LocalDateTime processedAt;

    @ManyToOne
    @JoinColumn(name="payer")
    private Users payer;

    @ManyToOne
    @JoinColumn(name="payee")
    private Users payee;
}
