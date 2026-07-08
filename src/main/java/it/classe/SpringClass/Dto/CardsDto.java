package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.ContoCorrente;
import it.classe.SpringClass.Model.Users;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CardsDto {

    private Integer id;
    private String telefono;
    private String cardNumber;
    private String cartType;
    private int amount;
    private int availableAmount;
    private List<UsersDto> users;
    private ContoCorrenteDto contoCorrente;
}
