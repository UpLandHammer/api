package med.voll.api.exceptions;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException(String message) {
        super(message);
    }
}
