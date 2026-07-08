package it.classe.SpringClass.Repository;

import it.classe.SpringClass.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByTaskName(String taskName);
    List<Task> findByTaskNameContaining(String keyword);
    //List<Task> findByCompletedFalse();
    //List<Task> findByCompletedTrue();
    List<Task> findByCompleted(boolean status);

    @Query("SELECT t FROM Task t WHERE t.taskName = ?1 AND t.completed = ?2")
    List<Task> trovaPerNomeEStato(String nome, boolean stato);
    @Query("SELECT t FROM Task t WHERE t.description LIKE %?1%")
    List<Task> cercaPerDescrizioneJPQL(String descrizione);

    @Query(value="SELECT * FROM task WHERE completed = true", nativeQuery = true)
    List<Task> trovaTaskCompletate();
    @Query(value="SELECT * FROM task WHERE completed = false", nativeQuery = true)
    List<Task> trovaTaskNonCompletate();

}