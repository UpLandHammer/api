package med.voll.api.usecase.paciente;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistePacientePorId {

    private final PacienteRepository pacienteRepository;

    public boolean executar(Long pacienteId) {
        return pacienteRepository.existsById(pacienteId);
    }
}
