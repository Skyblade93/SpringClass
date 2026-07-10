package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Scuola;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class AlunnoSeeder extends AbstractSeeder<Alunno> {

    public AlunnoSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Alunno> getEntityClass() {
        return Alunno.class;
    }

    @Override
    protected Alunno createEntity(int index) {

        Scuola[] scuole = Scuola.values();

        Alunno alunno = new Alunno();

        alunno.setNome(
                "AlunnoNome" + index
        );

        alunno.setCognome(
                "AlunnoCognome" + index
        );

        alunno.setVoto(
                60 + (index % 40)
        );

        alunno.setScuola(
                scuole[index % scuole.length]
        );

        return alunno;
    }
}