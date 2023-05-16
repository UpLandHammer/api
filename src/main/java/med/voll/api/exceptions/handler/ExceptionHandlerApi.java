package med.voll.api.exceptions.handler;

import lombok.*;
import med.voll.api.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler(AgendamentoConsultasException.class)
    public ResponseEntity<ErrorResponse> erroValidacao(AgendamentoConsultasException exception) {
        return ResponseEntity.badRequest().body(this.getErrorResponse(exception, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<ErrorResponse> erroGravarUsuario(UsuarioException exception) {
        return ResponseEntity.badRequest().body(this.getErrorResponse(exception, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(TokenApiException.class)
    public ResponseEntity<ErrorResponse> errorToken(TokenApiException ex) {
        return ResponseEntity.badRequest().body(this.getErrorResponse(ex, HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(InvalidAuthorizationException.class)
    public ResponseEntity<ErrorResponse> invalidToken(InvalidAuthorizationException ex) {
        return ResponseEntity.badRequest().body(this.getErrorResponse(ex, HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity getMedicoNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(GravarMedicoException.class)
    public ResponseEntity<ErrorResponse> getException(GravarMedicoException exception) {
        return ResponseEntity.badRequest().body(this.getErrorResponse(exception, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity getValidateException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getFieldErrors();

        return ResponseEntity.badRequest().body(fieldErrors.stream().map(ErrorResponse::new).collect(Collectors.toList()));
    }

    private ErrorResponse getErrorResponse(Exception ex, HttpStatus httpStatus) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .description(httpStatus.BAD_REQUEST.toString())
                .build();

    }

    @Builder
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ErrorResponse {
        String message;
        String description;

        public ErrorResponse(FieldError fieldError) {
            this.message = fieldError.getDefaultMessage();
            this.description = fieldError.getField();

        }
    }

}
