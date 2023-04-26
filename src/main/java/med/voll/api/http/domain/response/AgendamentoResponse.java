package med.voll.api.http.domain.response;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Builder
public class AgendamentoResponse {
    private Long id;
    private Long idMedico;
    private Long idPaciente;
    private LocalDateTime data;
}
