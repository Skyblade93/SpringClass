package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Users;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Profile("dev")
public class AutoSeeder extends AbstractSeeder<Auto> {

    private List<Users> users;
    private List<Alunno> alunni;

    public AutoSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Auto> getEntityClass() {
        return Auto.class;
    }

    @Override
    protected String skipReason() {

        users = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getResultList();


        if(users.isEmpty()) {
            return "Users table empty";
        }

        alunni = em.createQuery(
                "SELECT a FROM Alunno a",
                Alunno.class
        ).getResultList();

        if(alunni.isEmpty()) {
            return "Alunno table empty";
        }

        return null;
    }

    @Override
    protected Auto createEntity(int index) {

        Auto auto = new Auto();

        auto.setAnno(2000 + index);

        auto.setMarca(
                switch (index % 4) {
                    case 0 -> "Fiat";
                    case 1 -> "Ford";
                    case 2 -> "BMW";
                    default -> "Audi";
                }
        );

        auto.setModello(
                switch (index % 4) {
                    case 0 -> "Panda";
                    case 1 -> "Focus";
                    case 2 -> "Serie 1";
                    default -> "A3";
                }
        );

        auto.setColore(
                index % 2 == 0 ? "Blu" : "Grigio"
        );

        auto.setUser(
                users.get(index % users.size())
        );

        auto.setAlunno(
                alunni.get(index % alunni.size())
        );

        return auto;
    }
}