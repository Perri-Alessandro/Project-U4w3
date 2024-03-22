package perri.dao;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import perri.entities.Utente;
import perri.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtenteDAO {
    private static List<Utente> utenti = new ArrayList<>();
    private static Random random = new Random();
    private static Faker faker = new Faker();
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public List<Utente> creaUtenti() {

        for (int i = 1; i <= 7; i++) {
            String nome = faker.dragonBall().character();
            String cognome = faker.pokemon().name();
            utenti.add(new Utente(nome, cognome, LocalDate.of(1990 + i, 4 + i, 20 + i)));
        }
        return utenti;
    }

    public void saveUtenti(List<Utente> user) {
        try {
            em.getTransaction().begin();
            for (Utente utenti : user) {
                em.persist(utenti);
            }
            em.getTransaction().commit();

            System.out.println("UTENTI " + user + " SALVATI CORRETTAMENTE");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUtente(Utente user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            System.out.println("UTENTE SALVATO CORRETTAMENTE: " + user);
        } catch (NotFoundException e) {
            System.err.println("ERRORE NEL SALVARE L'UTENTE: " + e.getMessage());
        }
    }


    public Utente getByNumeroTessera(long numeroTessera) {
        Utente elemento = em.find(Utente.class, numeroTessera);
        if (elemento == null) throw new NotFoundException(numeroTessera);
        return elemento;
    }

    public void delete(long numeroTessera) {
        try {
            Utente trovato = this.getByNumeroTessera(numeroTessera);

            EntityTransaction tx = em.getTransaction();

            tx.begin();

            em.remove(trovato);

            tx.commit();

            System.out.println("UTENTE " + trovato.getName() + " ELIMINATO CON SUCCESSO");
        } catch (NotFoundException e) {
            System.err.println("ERRORE NEL CANCELLARE L'UTENTE: " + e.getMessage());
        }
    }


    public void visualizzaUtenti() {
        TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u", Utente.class);
        List<Utente> risultati = query.getResultList();
        for (Utente c : risultati) {
            System.out.println(c);
        }
    }
}

