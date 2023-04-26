package med.voll.api.usecase.consulta.validacoes;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.AgendamentoConsultasException;
import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.gateway.mysql.entity.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidarMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    public void validar(Consulta consulta) {

        Boolean aBoolean = consultaRepository.existsByMedicoIdAndData(consulta.getMedico().getId(), consulta.getData());

        if(aBoolean) {
            throw new AgendamentoConsultasException("Medico já possui agendamento na data");
        }

    }
}
