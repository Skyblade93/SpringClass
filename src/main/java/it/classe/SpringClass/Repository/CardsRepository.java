package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Integer> {


    Cards findByTelefono(String telefono);
    List<Cards> findByCartType(String cartType);
    Cards findByCardNumber(String cardNumber);

    @Query("select c from Cards c where c.amount= ?1")
    List<Cards> findByAmount(int amount);

    @Query("select c from Cards c where c.availableAmount = ?1")
    List<Cards> findByAvailableAmount(int availableAmount);

    @Query(value = "select * from Cards c where c.amount = ?1",nativeQuery = true)
    List<Cards> byAmount(int amount);

    @Query(value = "select * from Cards c where c.available_amount= ?1", nativeQuery = true)
    List<Cards> availableAmount(int availableAmount);

}
