package it.classe.SpringClass.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor


@Table(name = "task", schema = "class")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int task_id;

    private String taskName;
    private String description;
    private boolean completed;
/*
    @OneToMany
    @JoinColumn(name="task_id")
    private List<Alunno> alunnos;

    @ManyToOne
    @JoinColumn(name = "id_alunno")
    private Alunno alunno;
      */

    @ManyToMany
    @JoinTable(
            name = "task_alunno",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "alunno_id")
    )
    private List<Alunno> alunni = new ArrayList<>();



}
