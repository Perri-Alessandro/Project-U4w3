package perri;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import perri.dao.CatalogoDAO;
import perri.dao.UtenteDAO;
import perri.entities.Prestito;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class Application {

    private static final EntityManagerFactory emFactory = Persistence
            .createEntityManagerFactory("U4w3D5");
    private static final Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManager em = emFactory.createEntityManager();
        CatalogoDAO catDAO = new CatalogoDAO(em);
        UtenteDAO userDAO = new UtenteDAO(em);

        System.out.println("------------------------------------- ARCHIVIO BIBLIOTECA -----------------------------------");

//        CREAZIONE ARCHIVIO INIZIALE:
//        List<Catalogo> archivioIniziale = catDAO.creaArchivioIniziale();
//        catDAO.saveCat(archivioIniziale);

        //        catDAO.visualizzaCatalogo();

//      CREAZIONE NUOVO ELEMENTO IN ARICHIVIO:
//        Catalogo nuovoElemento = new Libro("Halving 2024", LocalDate.of(2024, 4, 16), 257, "Mew", "Storia");
//        catDAO.saveElement(nuovoElemento);

//         ELIMINAZIONE ELEMENTO IN ARICHIVIO TRAMITE ISBN:
//        catDAO.delete(355);

//        RICHERCA ELEMENTO IN ARICHIVIO TRAMITE ISBN:
//        System.out.println("ELEMENTO CERCATO: " + catDAO.getByIsbn(402).getTitolo());

//        List<Catalogo> elementiPerAnno = catDAO.elementoDaData(2022);
//        System.out.println("RICHERCA ELEMENTI IN ARICHIVIO TRAMITE DATA DI PUBBLICAZIONE: ");
//        elementiPerAnno.forEach(System.out::println);

//        List<Catalogo> libroDaAutore = catDAO.getByAuthor("Abra");
//        System.out.println("RICHERCA ELEMENTI IN ARICHIVIO TRAMITE AUTORE: ");
//        libroDaAutore.forEach(System.out::println);

//        List<Catalogo> elementoDaParteDiTitolo = catDAO.getByTitle("ymi");
//        System.out.println("RICHERCA ELEMENTI IN ARICHIVIO TRAMITE PARTE DI TITOLO: ");
//        elementoDaParteDiTitolo.forEach(System.out::println);

        //        CREAZIONE UTENTI INIZIALE:
//        List<Utente> utentiIniziali = userDAO.creaUtenti();
//        userDAO.saveUtenti(utentiIniziali);
//        userDAO.visualizzaUtenti();

        // CREAZIONE PRESTITI
        Prestito primo = new Prestito(userDAO.getByNumeroTessera(1), catDAO.getByIsbn(452), LocalDate.of(2024, 2, 8), LocalDate.of(2024, 3, 15), null);
        System.out.println("PRESTITO EFFETTUATO : " + primo);

        
        em.close();
        emFactory.close();
        sc.close();
    }
}
