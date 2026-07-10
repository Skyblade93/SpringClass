package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Model.ContoCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ContoCorrenteRepository extends JpaRepository<ContoCorrente, Integer> {

   // @Query("SELECT n FROM ContoCorrente n where n.nome=?1")
  //  ContoCorrente findByName(String nome);


   @Query("SELECT c FROM ContoCorrente c where c.email=?1")
    ContoCorrente findByEmail(String email);

    @Query("SELECT c FROM ContoCorrente c where c.cognome=?1")
    ContoCorrente findByCognome(String cognome);

/*
       @Query("select idconto  from contocorrente c where c.ID=?1")
        List<Cards> findCardByIntId(Integer id );

    //NATIVE

    @Query(value = "SELECT * FROM CONTOCORRENTE WHERE IDCONTO= ?1" ,nativeQuery = true)
    ContoCorrente findByIdNative(Integer id);

    @Query(value = "SELECT * FROM CONTOCORRENTE WHERE nome= ?1" ,nativeQuery = true)
    ContoCorrente findByNameNative(String nome);
*/


}
