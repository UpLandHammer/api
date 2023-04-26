package med.voll.api.usecase.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExisteMedicoPorId {

    private final MedicoRepository medicoRepository;

    public boolean executar(Long idMedico) {
        return medicoRepository.existsById(idMedico);
    }
}
