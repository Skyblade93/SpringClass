package it.classe.SpringClass.Dto;


import it.classe.SpringClass.Model.Scuola;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AlunnoDTO {

    private Long id;
    private String nome;
    private String cognome;
    private Integer voto;
    private Scuola scuola;


}
