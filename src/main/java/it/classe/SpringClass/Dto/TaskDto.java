package it.classe.SpringClass.Dto;
import it.classe.SpringClass.Model.Alunno;
import lombok.*;

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
    private List<Alunno> alunni;

}
