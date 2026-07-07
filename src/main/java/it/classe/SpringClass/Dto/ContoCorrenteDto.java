package it.classe.SpringClass.Dto;

import lombok.*;

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

}
