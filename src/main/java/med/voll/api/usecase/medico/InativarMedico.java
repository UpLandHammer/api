package med.voll.api.usecase.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InativarMedico {

    private final MedicoRepository medicoRepository;

    @Transactional
    public void executar(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
    }
}
