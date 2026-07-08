package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import it.classe.SpringClass.Model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends JpaRepository<Auto,Integer>{
    Auto findByModello(String modello);
    Auto findByColore(String colore);
    Auto findByMarca(String marca);
    Auto findByAnno(Integer anno);


    @Query("SELECT a FROM Auto a WHERE a.id = ?1")
    Auto AutoUsersId (Integer id);

    @Query(value = "SELECT a FROM Auto a WHERE a.colore = ?1")
    Auto coloreN(String colore);

    @Query(value = "SELECT * FROM Auto a WHERE a.marca = ?1",nativeQuery = true)
    Auto marcaN(String marca);

    @Query(value = "SELECT * FROM Auto a WHERE a.anno = ?1",nativeQuery = true)
    Auto annoN(Integer anno);

}
