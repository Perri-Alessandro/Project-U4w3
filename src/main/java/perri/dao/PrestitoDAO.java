package perri.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import perri.entities.Prestito;
import perri.entities.Utente;
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

