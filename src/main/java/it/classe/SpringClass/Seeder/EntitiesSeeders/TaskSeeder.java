package it.classe.SpringClass.Seeder.EntitiesSeeders;

import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Priorita;
import it.classe.SpringClass.Model.Task;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class TaskSeeder extends AbstractSeeder<Task> {

    private List<Alunno> alunni;

    public TaskSeeder(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    @Override
    protected String skipReason() {

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
    protected Task createEntity(int index) {

        Task task = new Task();

        task.setTaskName(
                "Task-" + index
        );

        task.setDescription(
                "Descrizione task " + index
        );

        task.setCompleted(
                index % 2 == 0
        );

        task.setDataScadenza(
                LocalDate.now().plusDays(index)
        );

        task.setPriorita(
                Priorita.values()[
                        index % Priorita.values().length
                        ]
        );

        List<Alunno> assegnati = new ArrayList<>();

        assegnati.add(
                alunni.get((index - 1) % alunni.size())
        );

        if(alunni.size() > 1 && index % 2 == 0) {

            assegnati.add(
                    alunni.get(index % alunni.size())
            );
        }

        task.setAlunno(assegnati);

        return task;
    }
}