package med.voll.api.usecase.consulta.validacoes;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.AgendamentoConsultasException;
import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.gateway.mysql.entity.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ValidarPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    public void validar(Consulta consulta) {

        LocalDateTime primeiroHorario = consulta.getData().withHour(7);
        LocalDateTime ultimoHorario = consulta.getData().withHour(18);

        Boolean exists = consultaRepository.existsByPacienteIdAndDataBetween(consulta.getPaciente().getId(), primeiroHorario, ultimoHorario);

        if(exists) {
            throw new AgendamentoConsultasException("Paciente já tem agendamento no dia");
        }

    }
}
