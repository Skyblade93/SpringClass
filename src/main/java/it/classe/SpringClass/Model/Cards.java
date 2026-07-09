package it.classe.SpringClass.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards",schema = "progetto")
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String telefono;
    private String cardNumber;
    private String cartType;
    private int amount;
    private int availableAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne
    private ContoCorrente contoCorrente;
}
