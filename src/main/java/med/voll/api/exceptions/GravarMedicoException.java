package med.voll.api.exceptions;

public class GravarMedicoException extends RuntimeException {
    public GravarMedicoException(String message, Throwable ex) {
        super(message, ex);
    }
}
