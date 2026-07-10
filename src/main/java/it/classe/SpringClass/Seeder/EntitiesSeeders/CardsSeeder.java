package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Model.ContoCorrente;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Seeder.EntitiesSeeders.AbstractSeeder;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class CardsSeeder extends AbstractSeeder<Cards> {

    private List<Users> users;
    private List<ContoCorrente> conti;

    public CardsSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Cards> getEntityClass() {
        return Cards.class;
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

        conti = em.createQuery(
                "SELECT c FROM ContoCorrente c",
                ContoCorrente.class
        ).getResultList();

        if (conti.isEmpty()) {
            return "ContoCorrente table empty";
        }

        return null;
    }

    @Override
    protected Cards createEntity(int index) {

        Cards card = new Cards();

        card.setTelefono(
                "3330000" + String.format("%03d", index)
        );

        card.setCardNumber(
                "4000-0000-0000-" +
                        String.format("%04d", index)
        );

        card.setCartType(
                index % 2 == 0
                        ? "VISA"
                        : "MASTERCARD"
        );

        card.setAmount(
                1000 + (index * 100)
        );

        card.setAvailableAmount(
                800 + (index * 100)
        );

        card.setUsers(
                users.get((index - 1) % users.size())
        );

        card.setContoCorrente(
                conti.get((index - 1) % conti.size())
        );

        return card;
    }
}