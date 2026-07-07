package it.classe.SpringClass.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="alunno",schema = "class")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Alunno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cognome;
    private Integer voto;
    @Enumerated(EnumType.STRING)
    private Scuola scuola;


    @ManyToMany(mappedBy = "alunni")
    private List<Task> task =  new ArrayList<>();
}
