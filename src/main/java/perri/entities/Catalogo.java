package perri.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Catalogo {
    @Id
    @GeneratedValue
    protected long codiceIsbn;
    protected String titolo;

    protected LocalDate annoDiPubblicazione;

    protected int numeroPagine;

    public Catalogo() {
    }

    ;

    public Catalogo(String titolo, LocalDate annoDiPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
    }


    public long getCodiceIsbn() {
        return codiceIsbn;
    }

    public void setCodiceIsbn(int codiceIsbn) {
        this.codiceIsbn = codiceIsbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(LocalDate annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                ", codiceIsbn=" + codiceIsbn +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
