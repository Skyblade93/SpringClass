package it.classe.SpringClass.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor


@Table(name = "task", schema = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    private String taskName;
    private String description;
    private boolean completed;


}
