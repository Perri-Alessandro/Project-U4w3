package perri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import perri.entities.Prestito;
import perri.exceptions.NotFoundException;

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

