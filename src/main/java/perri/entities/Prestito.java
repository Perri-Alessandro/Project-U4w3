package perri.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    protected Utente utente;

    @OneToOne
    @JoinColumn(name = "id_elemento_prestato")
    protected Catalogo elementoPrestato;

    protected LocalDate inizioPrestito;

    protected LocalDate restituzionePrevista;

    protected LocalDate restituzioneEffettiva;

    public Prestito() {
    }


    public Prestito(Utente utente, Catalogo elementoPrestato, LocalDate inizioPrestito, LocalDate restituzionePrevista, LocalDate restituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.inizioPrestito = inizioPrestito;
        this.restituzionePrevista = restituzionePrevista;
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Catalogo getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Catalogo elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getInizioPrestito() {
        return inizioPrestito;
    }

    public void setInizioPrestito(LocalDate inizioPrestito) {
        this.inizioPrestito = inizioPrestito;
    }

    public LocalDate getRestituzionePrevista() {
        return restituzionePrevista;
    }

    public void setRestituzionePrevista(LocalDate restituzionePrevista) {
        this.restituzionePrevista = restituzionePrevista;
    }

    public LocalDate getRestituzioneEffettiva() {
        return restituzioneEffettiva;
    }

    public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", inizioPrestito=" + inizioPrestito +
                ", restituzionePrevista=" + restituzionePrevista +
                ", restituzioneEffettiva=" + restituzioneEffettiva +
                '}';
    }
}
