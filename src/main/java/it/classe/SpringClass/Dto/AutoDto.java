package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AutoDto {
        private Integer id;
        private Integer anno;
        private String marca;
        private String modello;
        private String colore;
        private AlunnoDto alunno;
        private UsersDto users;

}

