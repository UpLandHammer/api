package med.voll.api.exceptions;

public class TokenApiException extends RuntimeException {

    public TokenApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
