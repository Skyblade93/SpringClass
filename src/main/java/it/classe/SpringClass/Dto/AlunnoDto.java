package it.classe.SpringClass.Dto;


import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Scuola;
import it.classe.SpringClass.Model.Task;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AlunnoDto {

    private Integer id;
    private String nome;
    private String cognome;
    private Integer voto;
    private Scuola scuola;
    private List<Auto> autos;
    private List<Task> task;

}
