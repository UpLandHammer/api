package med.voll.api.usecase.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.MedicoNotFoundException;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarMedicoPorId {

    private final MedicoRepository medicoRepository;

    public Medico executar(Long id) {
        return medicoRepository.findById(id).orElseThrow(() -> new MedicoNotFoundException(String.format("Medico de id %s não encontrado.", id)));
    }
}
