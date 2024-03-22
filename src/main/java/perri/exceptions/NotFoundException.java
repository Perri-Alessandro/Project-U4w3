package perri.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("EVENTO CON ID " + id + " NON è STATO TROVATO");
    }
}
