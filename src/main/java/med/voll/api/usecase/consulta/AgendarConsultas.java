package med.voll.api.usecase.consulta;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.AgendamentoConsultasException;
import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.gateway.mysql.entity.repository.ConsultaRepository;
import med.voll.api.usecase.consulta.validacoes.ValidadorAgendamentoConsulta;
import med.voll.api.usecase.medico.ExisteMedicoPorId;
import med.voll.api.usecase.medico.SelecionarMedicoLivreNaData;
import med.voll.api.usecase.paciente.ExistePacientePorId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AgendarConsultas {

    private final ConsultaRepository consultaRepository;
    private final ExistePacientePorId existePacientePorId;
    private final ExisteMedicoPorId existeMedicoPorId;
    private final SelecionarMedicoLivreNaData selecionarMedicoLivreNaData;

    private final List<ValidadorAgendamentoConsulta> validadores;

    public void executar(Consulta consulta) {
        if (!existePacientePorId.executar(consulta.getPaciente().getId())) {
            throw new AgendamentoConsultasException(String.format("Paciente %s inexistente", consulta.getPaciente().getId()));
        }

        if (!Objects.isNull(consulta.getMedico().getId()) && !existeMedicoPorId.executar(consulta.getMedico().getId())) {
            throw new AgendamentoConsultasException(String.format("Medico %s inexistente", consulta.getMedico().getId()));
        }


        selecionarMedicoLivreNaData.executar(consulta);

        validadores.forEach( validador -> validador.validar(consulta));

        consultaRepository.save(consulta);
    }


}
