package perri.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Elemento CON ID " + id + " NON è STATO TROVATO");
    }
}
