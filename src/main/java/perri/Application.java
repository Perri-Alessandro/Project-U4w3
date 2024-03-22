package perri;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import perri.dao.CatalogoDAO;

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

        System.out.println("------------------------------------- ARCHIVIO BIBLIOTECA -----------------------------------");

//        CREAZIONE INIZIALE ARCHIVIO: (decommentare a primo avvio e ricommentare dopo 1^ esecuzione)
//        List<Catalogo> archivioIniziale = catDAO.creaArchivioIniziale();
//        catDAO.save(archivioIniziale);

        catDAO.visualizzaCatalogo();

        em.close();
        emFactory.close();
        sc.close();
    }
}
