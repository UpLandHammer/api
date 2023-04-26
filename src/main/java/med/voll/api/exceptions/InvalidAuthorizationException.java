package med.voll.api.exceptions;

public class InvalidAuthorizationException extends RuntimeException {
    public InvalidAuthorizationException(String message) {
        super(message);
    }
}
