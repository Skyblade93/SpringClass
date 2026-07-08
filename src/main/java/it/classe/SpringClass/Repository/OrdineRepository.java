package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    Ordine findByProdotti(String prodotti);
    Ordine findByDatacaCreazione(LocalDate dataCreazione);
    Ordine findByImporto(double importo);
    Ordine findByQuantita(int quantita);

    @Query("SELECT o FROM Ordine o WHERE o.prodotti = ?1")
    Ordine prodotti(String prodotti);

    @Query("SELECT o FROM Ordine o WHERE o.importo = ?1")
    Ordine importo(double importo);

    @Query(value = "SELECT * FROM Ordine o WHERE o.prodotti = ?1",nativeQuery = true)
    Ordine prodottiN(String prodotti);

    @Query(value = "SELECT * FROM Ordine o WHERE o.importo = ?1", nativeQuery = true)
    Ordine importoN(double importo);

}
