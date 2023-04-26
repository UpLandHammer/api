package med.voll.api.usecase.paciente;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Paciente;
import med.voll.api.gateway.mysql.entity.repository.PacienteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GravarPaciente {

    private final PacienteRepository pacienteRepository;

    @Transactional
    public void executar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

}
