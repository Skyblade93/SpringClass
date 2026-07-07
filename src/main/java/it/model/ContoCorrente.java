package it.classe.SpringClass.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="ContoCorrente" ,schema="asset")
public class ContoCorrente {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "idConto")
   private Integer idConto;

   @Column(name = "iban")
    private String iban;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "email")
    private String email;

    @Column(name = "ccv")
    private String ccv;

}
