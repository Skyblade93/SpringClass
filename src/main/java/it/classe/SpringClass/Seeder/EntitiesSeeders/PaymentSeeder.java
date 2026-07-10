package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.Payment;
import it.classe.SpringClass.Model.PaymentMethod;
import it.classe.SpringClass.Model.PaymentStatus;
import it.classe.SpringClass.Model.Users;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("dev")
public class PaymentSeeder extends AbstractSeeder<Payment> {

    private List<Users> users;

    public PaymentSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Payment> getEntityClass() {
        return Payment.class;
    }

    @Override
    protected String skipReason() {

        users = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getResultList();

        if(users.isEmpty()) {
            return "Users table empty";
        }

        return null;
    }

    @Override
    protected Payment createEntity(int index) {

        Payment payment = new Payment();

        payment.setStatus(
                PaymentStatus.values()[
                        index % PaymentStatus.values().length
                        ]
        );

        payment.setPaymentMethod(
                PaymentMethod.values()[
                        index % PaymentMethod.values().length
                        ]
        );

        payment.setAmount(
                BigDecimal.valueOf(100 + (index * 25))
        );

        payment.setProcessedAt(
                LocalDateTime.now().minusDays(index)
        );

        payment.setPayer(
                users.get((index - 1) % users.size())
        );

        payment.setPayee(
                users.get(index % users.size())
        );

        return payment;
    }
}