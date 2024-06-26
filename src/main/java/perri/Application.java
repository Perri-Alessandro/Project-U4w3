package perri;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import perri.dao.CatalogoDAO;
import perri.dao.PrestitoDAO;
import perri.dao.UtenteDAO;

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
        PrestitoDAO prestDAO = new PrestitoDAO(em);

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
//        Prestito terzo = new Prestito(userDAO.getByNumeroTessera(1), catDAO.getByIsbn(305), LocalDate.of(2024, 1, 8), LocalDate.of(2024, 3, 10), null);
//        System.out.println("PRESTITO EFFETTUATO : " + terzo);
//        Prestito quarto = new Prestito(userDAO.getByNumeroTessera(7), catDAO.getByIsbn(155), LocalDate.of(2024, 2, 2), LocalDate.of(2024, 3, 8), null);
//        System.out.println("PRESTITO EFFETTUATO : " + quarto);
//        prestDAO.savePrestito(terzo);
//        prestDAO.savePrestito(quarto);

//        //RICERCA PRESTITI ATTIVI
//        List<Prestito> prestitiAttivi = prestDAO.prestitiAttiviPerUtente(1);
//        System.out.println("PRESTITI ATTIVI PER UTENTE CERCATO; " + prestitiAttivi);

//        RICERCA PRESTITI SCADUTI MA NON RESTITUITI
//        List<Prestito> prestitisìScaduti = prestDAO.prestitiScaduti();
//        System.out.println("PRESTITI SCADUTI MA NON RESTITUITI; " + prestitisìScaduti);


        em.close();
        emFactory.close();
        sc.close();
    }
}
