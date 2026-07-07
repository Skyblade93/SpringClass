package it.classe.SpringClass.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="alunni")
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

}
