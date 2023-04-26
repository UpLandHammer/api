package med.voll.api.http.domain.medico;

import lombok.*;
import med.voll.api.http.domain.enums.Especialidade;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosBasicosMedicoDTO {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;
}
