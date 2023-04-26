package med.voll.api.http.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import med.voll.api.http.domain.enums.Especialidade;

import java.time.LocalDateTime;

@Getter
public class AgendamentoConsultaDTO {

    private Long idMedico;
    @NotNull
    private Long idPaciente;
    @Future
    @NotNull
    private LocalDateTime data;

    private Especialidade especialidade;

}
