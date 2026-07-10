package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.ContoCorrente;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ContoCorrenteSeeder extends AbstractSeeder<ContoCorrente> {

    public ContoCorrenteSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<ContoCorrente> getEntityClass() {
        return ContoCorrente.class;
    }

    @Override
    protected ContoCorrente createEntity(int index) {

        ContoCorrente conto = new ContoCorrente();

        conto.setIban(
                "IT60X0542811101000000" +
                        String.format("%04d", index)
        );

        conto.setNome(
                "Nome" + index
        );

        conto.setCognome(
                "Cognome" + index
        );

        conto.setEmail(
                "conto" + index + "@example.com"
        );

        conto.setCcv(
                String.format("%03d", 100 + index)
        );

        return conto;
    }
}