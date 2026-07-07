package it.classe.SpringClass.Dto;

import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Model.Payment;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private List<Cards> cards;
    private List<Auto> auto_possedute ;
    private List<Payment> sentPayments ;
    private List<Payment> receivedPayments ;
}
