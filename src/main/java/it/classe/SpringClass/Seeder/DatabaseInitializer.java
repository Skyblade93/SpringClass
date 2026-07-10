package it.classe.SpringClass.Seeder;

import it.classe.SpringClass.Seeder.EntitiesSeeders.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DatabaseInitializer implements CommandLineRunner {

    private static final Logger log =
            LoggerFactory.getLogger(DatabaseInitializer.class);

    private final EntityManager em;

    private final UserSeeder userSeeder;
    private final AlunnoSeeder alunnoSeeder;
    private final ContoCorrenteSeeder contoCorrenteSeeder;
    private final AutoSeeder autoSeeder;
    private final OrdineSeeder ordineSeeder;
    private final PaymentSeeder paymentSeeder;
    private final TaskSeeder taskSeeder;
    private final CardsSeeder cardsSeeder;

    public DatabaseInitializer(
            EntityManager em,
            UserSeeder userSeeder,
            AlunnoSeeder alunnoSeeder,
            ContoCorrenteSeeder contoCorrenteSeeder,
            AutoSeeder autoSeeder,
            OrdineSeeder ordineSeeder,
            PaymentSeeder paymentSeeder,
            TaskSeeder taskSeeder,
            CardsSeeder cardsSeeder
    ) {
        this.em = em;
        this.userSeeder = userSeeder;
        this.alunnoSeeder = alunnoSeeder;
        this.contoCorrenteSeeder = contoCorrenteSeeder;
        this.autoSeeder = autoSeeder;
        this.ordineSeeder = ordineSeeder;
        this.paymentSeeder = paymentSeeder;
        this.taskSeeder = taskSeeder;
        this.cardsSeeder = cardsSeeder;
    }

    @Override
    @Transactional
    public void run(String... args) {

        log.info("Starting database seeding...");

        userSeeder.seed();
        alunnoSeeder.seed();
        contoCorrenteSeeder.seed();

        autoSeeder.seed();
        ordineSeeder.seed();
        paymentSeeder.seed();
        taskSeeder.seed();

        cardsSeeder.seed();

        log.info("Database seeding completed");
    }
}