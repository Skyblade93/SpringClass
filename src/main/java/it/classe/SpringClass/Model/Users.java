package it.classe.SpringClass.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "users", schema = "class")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String email;

    @ManyToMany(mappedBy = "users")
    private List<Cards> cards;

    @OneToMany(mappedBy = "Users")
    private List<Auto> auto_possedute = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Payment> payments = new ArrayList<>();
}
