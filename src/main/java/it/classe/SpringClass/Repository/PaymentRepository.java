package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
