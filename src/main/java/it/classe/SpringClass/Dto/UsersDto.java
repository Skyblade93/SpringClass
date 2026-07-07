package it.classe.SpringClass.Dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsersDto {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private List<CardsDto> cards;
   // private List<AutoDto> auto_possedute ;
    private List<PaymentDto> sentPayments ;
    private List<PaymentDto> receivedPayments ;
}
