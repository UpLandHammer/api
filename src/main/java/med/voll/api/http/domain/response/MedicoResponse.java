package med.voll.api.http.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.http.domain.enums.Especialidade;

@Getter
@Setter
@Builder
public class MedicoResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;
    private EnderecoResponse endereco;
    private boolean ativo;
}
