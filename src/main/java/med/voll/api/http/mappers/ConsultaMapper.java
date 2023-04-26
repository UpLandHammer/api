package med.voll.api.http.mappers;

import lombok.experimental.UtilityClass;
import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.Paciente;
import med.voll.api.http.domain.consulta.AgendamentoConsultaDTO;

import java.util.Objects;

@UtilityClass
public class ConsultaMapper {

    public static Consulta from(AgendamentoConsultaDTO agendamentoConsultaDTO) {

        return Consulta.builder()
                .medico(!Objects.isNull(agendamentoConsultaDTO.getIdMedico()) ? Medico.builder().id(agendamentoConsultaDTO.getIdMedico()).build() : null)
                .paciente(Paciente.builder().id(agendamentoConsultaDTO.getIdPaciente()).build())
                .data(agendamentoConsultaDTO.getData())
                .especialidade(!Objects.isNull(agendamentoConsultaDTO.getEspecialidade()) ? agendamentoConsultaDTO.getEspecialidade() : null)
                .build();

    }
}
