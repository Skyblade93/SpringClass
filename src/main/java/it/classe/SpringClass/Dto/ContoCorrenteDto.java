package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.Cards;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ContoCorrenteDto {

    private Integer idConto;
    private String iban;
    private String nome;
    private String cognome;
    private String email;
    private String ccv;
    private List<Cards> cards;

}
