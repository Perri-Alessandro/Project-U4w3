package perri.dao;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import perri.entities.Catalogo;
import perri.entities.Libro;
import perri.entities.Periodicità;
import perri.entities.Rivista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GestioneCatalogo {

    private static List<Catalogo> archivio = new ArrayList<>();
    private static Periodicità[] tuttePeriodicità = Periodicità.values();
    private static Random random = new Random();
    private static Faker faker = new Faker();
    private static EntityManager em = null;

    public GestioneCatalogo(EntityManager em) {
        this.em = em;
    }

    public static List<Catalogo> creaArchivioIniziale() {

        for (int i = 1; i <= 5; i++) {
            String titolo = faker.yoda().quote();
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

    public static List<Catalogo> gestisciCatalogo(Scanner sc) {
        List<Catalogo> catalogo = creaArchivioIniziale();

        int scelta;

        do {
            System.out.println("SCEGLI UN OPZIONE: ");
            System.out.println("1 PER VISUALIZZARE IL CATALOGO");
            System.out.println("2 PER RIMUOVERE 1 ELEMENTO DAL CATALOGO");
            System.out.println("3 PER AGGIUNGER 1 ELEMENTO AL CATALOGO");
            System.out.println("4 PER CERCARE 1 ELEMENTO TRAMITE IL SUO CODICE ISBN");
            System.out.println("5 PER CERCARE 1 ELEMENTO TRAMITE LA SUA DATA DI PUBBLICAZIONE");
            System.out.println("6 PER CERCARE 1 LIBRO TRAMITE IL SUO AUTORE");
            System.out.println("0 PER CHIUDERE IL PROGRAMMA");


            scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {
                case 0:
                    System.out.println("GRAZIE ED ARRIVEDERCI!");
                    break;
                case 1:
                    visualizzaCatalogo();
                    break;

                case 2:
                    rimuoviElemento(sc, catalogo);
                    break;

                case 3:
                    System.out.println("1 PER AGGIUNGER 1 LIBRO");
                    System.out.println("2 PER AGGIUNGERE UNA RIVISTA");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    if (tipo == 1) {
                        System.out.println("AGGIUNGI LIBRO - INSERISCI TITOLO, DATA DI PUBBLICAZIONE (yyyy-mm-dd), NUMERO PAGINE, AUTORE E GENERE: ");

                        String titolo = sc.nextLine();
                        String dataStringa = null;
                        LocalDate dataPubblicazione = null;
                        while (dataPubblicazione == null) {
                            try {
                                dataStringa = sc.nextLine();
                                dataPubblicazione = LocalDate.parse(dataStringa);
                            } catch (DateTimeParseException e) {
                                System.err.println("FORMATO NON VALIDO, PER FAVORE INSERISCI LA DATA NEL FORMATO CORRETTO (yyyy-mm-dd).");
                            }
                        }
                        dataPubblicazione = LocalDate.parse(dataStringa);
                        int pagine = sc.nextInt();
                        sc.nextLine();
                        String autore = sc.nextLine();
                        String genere = sc.nextLine();

                        Libro nuovoLibro = new Libro(titolo, dataPubblicazione, pagine, autore, genere);
                        catalogo.add(nuovoLibro);
                    } else if (tipo == 2) {
                        System.out.println("AGGIUNGI RIVISTA - INSERISCI TITOLO, DATA DI PUBBLICAZIONE (yyyy-mm-dd), NUMERO PAGINE E PERIODICITà (0 settimanale, 1 mensile, 2 semestrale): ");

                        String titolo = sc.nextLine();
                        String dataStringa = null;
                        LocalDate dataPubblicazione = null;
                        while (dataPubblicazione == null) {
                            try {
                                dataStringa = sc.nextLine();
                                dataPubblicazione = LocalDate.parse(dataStringa);
                            } catch (DateTimeParseException e) {
                                System.err.println("FORMATO NON VALIDO, PER FAVORE INSERISCI LA DATA NEL FORMATO CORRETTO (yyyy-mm-dd).");
                            }
                        }
                        dataPubblicazione = LocalDate.parse(dataStringa);
                        int pagine = sc.nextInt();
                        int periodicità;
                        Periodicità periodicitàScelta = null;
                        do {
                            periodicità = sc.nextInt();
                            try {
                                periodicitàScelta = Periodicità.values()[periodicità];
                                break;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.err.println("SELEZIONE NON VALIDA; 0 PER PERIODICITà SETTIMANALE, 1 PER MENSILE E 2 PER SEMESTRALE");
                            }
                        } while (true);
                        Rivista nuovaRivista = new Rivista(titolo, dataPubblicazione, pagine, periodicitàScelta);
                        catalogo.add(nuovaRivista);
                    }
                case 4:
                    cercaConIsbn(sc, catalogo);
                    break;

                case 5:
                    cercaConAnno(sc, catalogo);
                    break;

                case 6:
                    cercaConAutore(sc, catalogo);
                    break;

                default:
                    System.out.println("SCELTA NON VALIDA");
            }
        } while (scelta != 0);
        return catalogo;
    }

    private static void rimuoviElemento(Scanner sc, List<Catalogo> catalogo) {
        System.out.println("INSERISCI IL CODCIE ISBN DELL'ELEMENTO DA RIMUOVERE (da 10000 in ordine crescente) : ");
        int x = sc.nextInt();
        Catalogo elementoDatogliere = catalogo.stream().filter(prod -> prod.getCodiceIsbn() == x)
                .findFirst().orElse(null);

        if (elementoDatogliere != null) {
            catalogo.remove(elementoDatogliere);
            System.out.println(" ELEMENTO CON CODICE ISBN " + x + " RIMOSSO CON SUCCESSO.");
        } else {
            System.err.println("CODICE ISBN " + x + " NON VALIDO, RIPROVARE: ");
        }

    }

    private static void cercaConIsbn(Scanner sc, List<Catalogo> catalogo) {
        System.out.println("INSERISCI IL CODCIE ISBN DELL'ELEMENTO DA CERCARE: ");
        int codice = sc.nextInt();

        Catalogo elementoTrovato = catalogo.stream().filter(elemento -> elemento.getCodiceIsbn() == codice).findAny().orElse(null);

        if (elementoTrovato != null) {
            System.out.println("ELEMENTO TROVATO: " + elementoTrovato);
        } else {
            System.err.println("NESSUN ELEMENTO TROVATO CON L'ISBN SPECIFICATO");
        }
    }

    private static void cercaConAnno(Scanner sc, List<Catalogo> catalogo) {
        System.out.println("INSERISCI LA DATA DI PUBBLICAZIONE DI ELEMENTO DA CERCARE: ");
        String dataInput = sc.nextLine();

        LocalDate dataRicerca;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dataRicerca = LocalDate.parse(dataInput, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("FORMATO DATA NON VALIDO");
            return;
        }
        Catalogo elementoTrovato = catalogo.stream().filter(elem -> elem.getAnnoDiPubblicazione().equals(dataRicerca)).findAny().orElse(null);

        if (elementoTrovato != null) {
            System.out.println("ELEMENTO TROVATO: " + elementoTrovato);
        } else {
            System.err.println("NESSUN ELEMENTO TROVATO CON LA DATA SPECIFICATO");
        }
    }

    private static void cercaConAutore(Scanner sc, List<Catalogo> catalogo) {
        System.out.println("INSERISCI AUTORE DI ELEMENTO DA CERCARE: ");
        String autore = sc.nextLine();

        List<Libro> elementoTrovato = catalogo.stream().filter(elem -> elem instanceof Libro)
                .map(elem -> (Libro) elem).filter(libro -> libro.autore.equalsIgnoreCase(autore)).collect(Collectors.toList());

        if (elementoTrovato != null) {
            System.out.println("ELEMENTO TROVATO: " + elementoTrovato);
        } else {
            System.err.println("NESSUN ELEMENTO TROVATO PER QUESTO AUTORE");
        }
    }

    public static List<Catalogo> getArchivio() {
        return archivio;
    }

    private static void visualizzaCatalogo() {
        TypedQuery<Catalogo> query = em.createQuery("SELECT c FROM Catalogo c", Catalogo.class);
        List<Catalogo> risultati = query.getResultList();
        for (Catalogo c : risultati) {
            System.out.println(c);
        }
    }
}
