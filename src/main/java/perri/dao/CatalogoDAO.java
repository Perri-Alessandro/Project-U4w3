package perri.dao;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import perri.entities.Catalogo;
import perri.entities.Libro;
import perri.entities.Periodicità;
import perri.entities.Rivista;

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

    public void save(List<Catalogo> archivio) {
        em.getTransaction().begin();
        for (Catalogo catalogo : archivio) {
            em.persist(catalogo);
        }
        em.getTransaction().commit();

        System.out.println("ELEMENTO " + archivio + " SALVATO CORRETTAMENTE");
    }

//    public Evento getById(long id) {
//        Evento evento = em.find(Evento.class, id);
//        if (evento == null) throw new NotFoundException(id);
//        return evento;
//    }
//
//    public void delete(long id) {
//        Evento trovato = this.getById(id);
//
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        em.remove(trovato);
//
//        tx.commit();
//
//        System.out.println("EVENTO " + trovato.getTitolo() + " ELIMINATO CON SUCCESSO");
//    }
//
//    public List<Boolean> getConcertiStreaming() {
//        TypedQuery<Boolean> query = em.createQuery("SELECT c.streaming FROM Concerto c", Boolean.class);
//        return query.getResultList();
//    }
//
//    public List<Concerto> getConcertiGenere(ConcertoType genere) {
//        TypedQuery<Concerto> q = em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere", Concerto.class);
//        q.setParameter("genere", genere);
//        return q.getResultList();
//    }
//
//    public List<PartitaDiCalcio> getPartiteVinteInCasa() {
//        TypedQuery<PartitaDiCalcio> p = em.createNamedQuery("getPartiteVinteInCasa", PartitaDiCalcio.class);
//        return p.getResultList();
//    }
//
//    public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
//        TypedQuery<PartitaDiCalcio> p = em.createNamedQuery("getPartiteVinteInTrasferta", PartitaDiCalcio.class);
//        return p.getResultList();
//    }

    public void visualizzaCatalogo() {
        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c", Catalogo.class);
        List<Catalogo> risultati = query.getResultList();
        for (Catalogo c : risultati) {
            System.out.println(c);
        }
    }
}
