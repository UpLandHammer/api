package med.voll.api.usecase.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.AgendamentoConsultasException;
import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SelecionarMedicoLivreNaData {

    private final MedicoRepository medicoRepository;

    public void executar(Consulta consulta) {

        if (Objects.isNull(consulta.getEspecialidade()) && Objects.isNull(consulta.getMedico().getId())) {
            throw new AgendamentoConsultasException("Especialidade do médico é obrigatória quando não for informado o médico");
        }


        Medico medico = medicoRepository.escolherMedicoLivreAleatoriamenteNaData(consulta.getMedico().getEspecialidade(), consulta.getData());

        consulta.setMedico(medico);
    }
}
