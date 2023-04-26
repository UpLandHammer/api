package med.voll.api.http.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import med.voll.api.http.domain.endereco.DadosEnderecoDTO;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cpf;

    @Valid
    @NotNull
    private DadosEnderecoDTO endereco;
}
