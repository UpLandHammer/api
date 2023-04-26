package med.voll.api.http.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosEnderecoDTO {

        @NotBlank
        private String logradouro;
        @NotBlank
        private String bairro;
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        private String cep;
        @NotBlank
        private String cidade;
        @NotBlank
        private String uf;
        private String complemento;
        private String numero;
}
