package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Ordine;
import it.classe.SpringClass.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    Ordine findByDataCreazione(LocalDateTime dataCreazione);
    List<Ordine> findByImporto(double importo);
    List<Ordine> findByQuantita(int quantita);
    List<Ordine> findByUsers(Users users);

    @Query("SELECT o FROM Ordine o WHERE o.prodotti = ?1")
    List<Ordine> cercaProdotti(String prodotti);

    @Query("SELECT o FROM Ordine o ORDER BY o.importo DESC")
    List<Ordine> ordiniDalPiuCostoso();

    @Query(value = "SELECT * FROM ordine WHERE quantita > ?1", nativeQuery = true)
    List<Ordine> cercaPerQuantitaMaggiore(int quantita);

    @Query(value = "SELECT * FROM ordine WHERE id_users = ?1 ORDER BY dataCreazione DESC", nativeQuery = true)
    List<Ordine> ultimiOrdini(Integer idUsers);

}
