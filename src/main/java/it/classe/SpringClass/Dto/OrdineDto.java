package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrdineDto {

    private Integer id;
    private String prodotti;
    private LocalDateTime dataCreazione;
    private double importo;
    private int quantita;
    private UsersDto users;
}
