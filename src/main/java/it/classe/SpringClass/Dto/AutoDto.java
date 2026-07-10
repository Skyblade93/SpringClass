package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.Users;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AutoDto {
        private Integer id;
        private Integer anno;
        private String marca;
        private String modello;
        private String colore;
        private AlunnoDto alunno;
        private UsersDto users;

}

