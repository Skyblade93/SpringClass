package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Payment;
import it.classe.SpringClass.Model.PaymentMethod;
import it.classe.SpringClass.Model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // JPA
    List<Payment> findByPayerId(Integer payerId);
    List<Payment> findByPayeeId(Integer payeeId);

    // Hibernate

    @Query("""
       SELECT p
       FROM Payment p
       WHERE p.payer.username = :username
       """)
    List<Payment> findPaymentsByPayerUsername(String username);

    @Query("""
       SELECT p
       FROM Payment p
       WHERE p.status = :status
       AND p.amount > :amount
       """)
    List<Payment> findByStatusAndMinAmount(
            PaymentStatus status,
            BigDecimal amount
    );

    // SQL

    @Query(value = """
        SELECT COALESCE(SUM(amount),0)
        FROM class.payments
        WHERE payer = :userId
        """,
            nativeQuery = true)
    BigDecimal getTotalSpentByUser(Integer userId);

    @Query(value = """
        SELECT COALESCE(SUM(amount),0)
        FROM class.payments
        WHERE payee = :userId
        """,
            nativeQuery = true)
    BigDecimal getTotalReceivedByUser(Integer userId);
}