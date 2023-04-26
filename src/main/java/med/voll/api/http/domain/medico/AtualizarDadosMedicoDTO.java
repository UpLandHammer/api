package med.voll.api.http.domain.medico;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.http.domain.endereco.DadosEnderecoDTO;

@Builder
@Getter
@Setter
public class AtualizarDadosMedicoDTO {

    @NotNull
    private Long id;
    private String nome;
    private String telefone;
    private DadosEnderecoDTO dadosEnderecoDTO;
}
