package med.voll.api.http.domain.consulta;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DetalhamentoConsultaResponse {

    private Long id;
    private Long idMedico;
    private Long idPaciente;
    private LocalDateTime data;

}
