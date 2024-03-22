package perri.dao;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import perri.entities.Catalogo;
import perri.entities.Libro;
import perri.entities.Periodicità;
import perri.entities.Rivista;
import perri.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CatalogoDAO {
    private static List<Catalogo> archivio = new ArrayList<>();
    private static Random random = new Random();
    private static Faker faker = new Faker();
    private static Periodicità[] tuttePeriodicità = Periodicità.values();
    private final EntityManager em;

    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public List<Catalogo> creaArchivioIniziale() {

        for (int i = 1; i <= 5; i++) {
            String titolo = faker.zelda().character();
            String autore = faker.pokemon().name();
            archivio.add(new Libro(titolo, LocalDate.of(2018 + i, 4 + i, 20 + i), 100 + i * 10, autore, "Genere " + i));
        }
        for (int i = 1; i <= 5; i++) {
            Periodicità periodCasuale = tuttePeriodicità[random.nextInt(tuttePeriodicità.length)];
            String titolo = faker.yoda().quote();
            archivio.add(new Rivista(titolo, LocalDate.of(2018 + i, 7 + i, 15 + i), 50 + i * 5, periodCasuale));
        }
        return archivio;
    }

    public void saveCat(List<Catalogo> archivio) {
        try {
            em.getTransaction().begin();
            for (Catalogo catalogo : archivio) {
                em.persist(catalogo);
            }
            em.getTransaction().commit();

            System.out.println("ARCHIVIO " + archivio + " SALVATO CORRETTAMENTE");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveElement(Catalogo elemento) {
        try {
            em.getTransaction().begin();
            em.persist(elemento);
            em.getTransaction().commit();

            System.out.println("ELEMENTO SALVATO CORRETTAMENTE: " + elemento);
        } catch (NotFoundException e) {
            System.err.println("ERRORE NEL SALVARE L'ELEMENTO: " + e.getMessage());
        }
    }


    public Catalogo getByIsbn(long codiceIsbn) {
        Catalogo elemento = em.find(Catalogo.class, codiceIsbn);
        if (elemento == null) throw new NotFoundException(codiceIsbn);
        return elemento;
    }

    public void delete(long codiceIsbn) {
        try {
            Catalogo trovato = this.getByIsbn(codiceIsbn);

            EntityTransaction tx = em.getTransaction();

            tx.begin();

            em.remove(trovato);

            tx.commit();

            System.out.println("ELEMENTO " + trovato.getTitolo() + " ELIMINATO CON SUCCESSO");
        } catch (NotFoundException e) {
            System.err.println("ERRORE NEL CANCELLARE L'ELEMENTO: " + e.getMessage());
        }
    }

    public List<Catalogo> elementoDaData(int anno) {
        TypedQuery<Catalogo> q = em.createQuery("SELECT c FROM Catalogo c WHERE EXTRACT(YEAR FROM c.annoDiPubblicazione) = :anno", Catalogo.class);
        q.setParameter("anno", anno);
        return q.getResultList();
    }

    public List<Catalogo> getByAuthor(String author) {
        TypedQuery<Catalogo> q = em.createQuery("SELECT c FROM Catalogo c WHERE c.autore = :author", Catalogo.class);
        q.setParameter("author", author);
        return q.getResultList();
    }

    public List<Catalogo> getByTitle(String titolo) {
        TypedQuery<Catalogo> q = em.createQuery("SELECT c FROM Catalogo c WHERE c.titolo LIKE :titolo", Catalogo.class);
        q.setParameter("titolo", "%" + titolo + "%");
        return q.getResultList();
    }

    public void visualizzaCatalogo() {
        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c", Catalogo.class);
        List<Catalogo> risultati = query.getResultList();
        for (Catalogo c : risultati) {
            System.out.println(c);
        }
    }
}
