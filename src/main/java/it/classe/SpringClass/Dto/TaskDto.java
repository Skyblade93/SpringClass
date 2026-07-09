package it.classe.SpringClass.Dto;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Priorita;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskDto {

    private int task_id;
    private String taskName;
    private String description;
    private boolean completed;
    private LocalDate dataScadenza;
    private Priorita priorita;

    private List<Alunno> alunno;

}
