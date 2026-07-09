package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Alunno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunnoRepository extends JpaRepository<Alunno, Integer> {

    Alunno findByNome(String nome);

}