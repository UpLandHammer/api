package med.voll.api.http.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import med.voll.api.http.domain.endereco.DadosEnderecoDTO;
import med.voll.api.http.domain.enums.Especialidade;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO implements Serializable {

        @NotBlank
        private String nome;
        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String telefone;

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        private String crm;
        @NotNull
        private Especialidade especialidade;
        @NotNull
        @Valid
        private DadosEnderecoDTO endereco;

}
