package med.voll.api.usecase.consulta.validacoes;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.AgendamentoConsultasException;
import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ValidarMedicoAtivo implements ValidadorAgendamentoConsulta {

    private final MedicoRepository repository;

    public void validar(Consulta consulta){

        if(Objects.isNull(consulta.getMedico())) {
            return;
        }

        Boolean isAtivo = repository.findAtivoById(consulta.getMedico().getId())
                .orElseThrow(() -> new AgendamentoConsultasException("Medico inexistente"));

        if (!isAtivo) {
            throw new AgendamentoConsultasException("Medico inativo");
        }
    }
}
