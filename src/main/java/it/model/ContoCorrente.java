package it.model;

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


    public void setIdConto(Integer idConto) {
        this.idConto = idConto;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public Integer getIdConto() {
        return idConto;
    }

    public String getIban() {
        return iban;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getCcv() {
        return ccv;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ContoCorrente{" +
                "idConto=" + idConto +
                ", iban='" + iban + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", ccv='" + ccv + '\'' +
                '}';
    }

    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ContoCorrente that = (ContoCorrente) object;
        return java.util.Objects.equals(idConto, that.idConto) && java.util.Objects.equals(iban, that.iban) && java.util.Objects.equals(nome, that.nome) && java.util.Objects.equals(cognome, that.cognome) && java.util.Objects.equals(email, that.email) && java.util.Objects.equals(ccv, that.ccv);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), idConto, iban, nome, cognome, email, ccv);
    }
}
