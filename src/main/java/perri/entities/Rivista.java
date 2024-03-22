package perri.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "rivista")
public class Rivista extends Catalogo {

    @Enumerated(EnumType.STRING)
    public Periodicità periodicità;

    public Rivista() {
    }

    public Rivista(String titolo, LocalDate annoDiPubblicazione, int numeroPagine, Periodicità periodicità) {
        super(titolo, annoDiPubblicazione, numeroPagine);
        this.periodicità = periodicità;
    }

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "titolo='" + titolo + '\'' +
                ", codiceIsbn=" + codiceIsbn +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", Periodicità=" + periodicità +
                '}';
    }
}
