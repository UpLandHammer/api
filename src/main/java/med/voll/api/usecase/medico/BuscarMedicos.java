package med.voll.api.usecase.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BuscarMedicos {

    private final MedicoRepository medicoRepository;

    @Transactional
    public Page<Medico> executar(Pageable pageable) {
        return medicoRepository.findAllByAtivoTrue(pageable);
    }
}
