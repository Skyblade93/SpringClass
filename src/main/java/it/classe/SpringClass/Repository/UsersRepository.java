package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Users paperino(String email);

    @Query(value = "SELECT * FROM Users t WHERE t.name = ?1",nativeQuery = true)
    Users pippo(String nome);



}
