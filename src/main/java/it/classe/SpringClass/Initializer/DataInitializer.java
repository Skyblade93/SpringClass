package it.classe.SpringClass.Initializer;

import it.classe.SpringClass.Model.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final EntityManager em;

    public DataInitializer(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // evita di inserire dati duplicati se il DB non è vuoto
        Long userCount = em.createQuery("select count(u) from Users u", Long.class).getSingleResult();
        log.info("Existing users in DB: {}", userCount);
        if (userCount != null && userCount > 0) {
            log.info("DB già popolato: skipping data initialization");
            return;
        }

        List<Users> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Users u = new Users();
            u.setUsername("user" + i);
            u.setPassword("pass" + i);
            u.setEmail("user" + i + "@example.com");
            em.persist(u);
            users.add(u);
        }
        log.info("Persisted {} users", users.size());

        List<ContoCorrente> conti = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ContoCorrente c = new ContoCorrente();
            c.setIban("IT" + String.format("%02d", i) + "IBAN");
            c.setNome("Nome" + i);
            c.setCognome("Cognome" + i);
            c.setEmail("cont" + i + "@example.com");
            c.setCcv(String.format("%03d", 100 + i));
            em.persist(c);
            conti.add(c);
        }
        log.info("Persisted {} conti correnti", conti.size());

        List<Cards> cards = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Cards card = new Cards();
            card.setTelefono("33300000" + i);
            card.setCardNumber("4000-0000-0000-" + String.format("%04d", i));
            card.setCartType(i % 2 == 0 ? "Visa" : "MasterCard");
            card.setAmount(1000 + i);
            card.setAvailableAmount(800 + i);
            card.setContoCorrente(conti.get(i - 1));
            Users cu = new Users();
            if (i % 3 == 0) cu = users.get(i % users.size());
            card.setUsers(cu);
            em.persist(card);
            cards.add(card);
        }
        log.info("Persisted {} cards", cards.size());

        for (int i = 1; i <= 10; i++) {
            Auto a = new Auto();
            a.setAnno(2000 + i);
            a.setMarca("Marca" + i);
            a.setModello("Modello" + i);
            a.setColore(i % 2 == 0 ? "Blu" : "Rosso");
            a.setUser(users.get((i - 1) % users.size()));
            em.persist(a);
        }
        log.info("Persisted 10 autos");

        List<Alunno> alunni = new ArrayList<>();
        Scuola[] scuole = Scuola.values();
        for (int i = 1; i <= 10; i++) {
            Alunno al = new Alunno();
            al.setNome("AlunnoNome" + i);
            al.setCognome("AlunnoCognome" + i);
            al.setVoto(60 + (i % 41));
            al.setScuola(scuole[i % scuole.length]);
            em.persist(al);
            alunni.add(al);
        }
        log.info("Persisted {} alunni", alunni.size());

        for (int i = 1; i <= 10; i++) {
            Ordine o = new Ordine();
            o.setProdotti("Prodotto" + i);
            o.setDataCreazione(LocalDateTime.now());
            o.setImporto(10.5 * i);
            o.setQuantita(i);
            o.setUsers(users.get((i - 1) % users.size()));
            em.persist(o);
        }
        log.info("Persisted 10 ordini");

        for (int i = 1; i <= 10; i++) {
            Payment p = new Payment();
            p.setStatus(PaymentStatus.Successful);
            p.setPaymentMethod(PaymentMethod.Visa);
            p.setAmount(BigDecimal.valueOf(100.0 + i));
            p.setProcessedAt(LocalDateTime.now());
            p.setPayer(users.get((i - 1) % users.size()));
            p.setPayee(users.get(i % users.size()));
            em.persist(p);
        }
        log.info("Persisted 10 payments");

        for (int i = 1; i <= 10; i++) {
            Task t = new Task();
            t.setTaskName("Task" + i);
            t.setDescription("Desc " + i);
            t.setCompleted(i % 2 == 0);
            // assegna alcuni alunni alla task (1-2)
            List<Alunno> assigned = new ArrayList<>();
            assigned.add(alunni.get((i - 1) % alunni.size()));
            if (i % 2 == 0) assigned.add(alunni.get(i % alunni.size()));
            t.setAlunno(assigned);
            em.persist(t);
        }
        log.info("Persisted 10 tasks");

        em.flush();
        log.info("Data initialization completed and flushed to DB");
    }
}

