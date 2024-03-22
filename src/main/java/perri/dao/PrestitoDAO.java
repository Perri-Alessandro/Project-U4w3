package perri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import perri.entities.Prestito;
import perri.exceptions.NotFoundException;

import java.util.List;

public class PrestitoDAO {

    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void savePrestito(Prestito prestito) {
        try {
            em.getTransaction().begin();
            em.persist(prestito);
            em.getTransaction().commit();

            System.out.println("PRESTITO SALVATO CORRETTAMENTE: " + prestito);
        } catch (NotFoundException e) {
            System.err.println("ERRORE NEL SALVARE IL PRESTITO: " + e.getMessage());
        }
    }


    public Prestito getById(long id) {
        Prestito elemento = em.find(Prestito.class, id);
        if (elemento == null) throw new NotFoundException(id);
        return elemento;
    }

    public List<Prestito> prestitiAttiviPerUtente(long numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.restituzioneEffettiva IS NULL",
                Prestito.class);
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }


    public void delete(long id) {
        try {
            Prestito trovato = this.getById(id);

            EntityTransaction tx = em.getTransaction();

            tx.begin();

            em.remove(trovato);

            tx.commit();

            System.out.println("PRESTITO DI UTENTE " + trovato.getUtente().getName() + " ELIMINATO CON SUCCESSO");
        } catch (NotFoundException e) {
            System.err.println("ERRORE NEL CANCELLARE IL PRESTITO DI UTENTE : " + e.getMessage());
        }
    }
}

