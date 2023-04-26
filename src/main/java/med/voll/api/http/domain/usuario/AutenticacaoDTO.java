package med.voll.api.http.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AutenticacaoDTO {

    @NotBlank
    @NotNull
    private String login;
    @NotBlank
    @NotNull
    private String senha;
}
