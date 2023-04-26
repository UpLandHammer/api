package med.voll.api.usecase.paciente;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Paciente;
import med.voll.api.gateway.mysql.entity.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BuscarPacientes {

    private final PacienteRepository pacienteRepository;

    @Transactional
    public Page<Paciente> executar(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }
}
