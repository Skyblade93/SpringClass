package it.classe.SpringClass.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "auto", schema = "class")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer anno;
    private String marca;
    private String modello;
    private String colore;

    @ManyToOne
    @JoinColumn(name="auto_possedute")
    private Users user;

    //Alunno e auto
    @ManyToOne
    @JoinColumn(name = "alunno_id")
    private Alunno alunno;

}
