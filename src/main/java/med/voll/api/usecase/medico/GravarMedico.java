package med.voll.api.usecase.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.GravarMedicoException;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GravarMedico {

    private final MedicoRepository medicoRepository;

    @Transactional
    public Medico execute(Medico medico) {
        try {
            return medicoRepository.save(medico);
        } catch (Exception exception) {
            throw new GravarMedicoException(exception.getMessage());
        }
    }
}
