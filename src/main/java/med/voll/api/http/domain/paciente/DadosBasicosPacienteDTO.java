package med.voll.api.http.domain.paciente;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DadosBasicosPacienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
}
