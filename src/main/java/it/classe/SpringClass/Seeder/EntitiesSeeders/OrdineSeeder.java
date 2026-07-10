package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.Ordine;
import it.classe.SpringClass.Model.Users;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile("dev")
public class OrdineSeeder extends AbstractSeeder<Ordine> {

    private List<Users> users;

    public OrdineSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Ordine> getEntityClass() {
        return Ordine.class;
    }

    @Override
    protected String skipReason() {

        users = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getResultList();

        if (users.isEmpty()) {
            return "Users table empty";
        }

        return null;
    }

    @Override
    protected Ordine createEntity(int index) {

        Ordine ordine = new Ordine();

        ordine.setProdotti(
                "Prodotto-" + index
        );

        ordine.setDataCreazione(
                LocalDateTime.now().minusDays(index)
        );

        ordine.setImporto(
                10.50 * index
        );

        ordine.setQuantita(
                (index % 5) + 1
        );

        ordine.setUsers(
                users.get((index - 1) % users.size())
        );

        return ordine;
    }
}