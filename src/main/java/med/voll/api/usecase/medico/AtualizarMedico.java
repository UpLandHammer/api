package med.voll.api.usecase.medico;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.ResourceNotFoundException;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.repository.MedicoRepository;
import med.voll.api.http.domain.parser.MedicoParser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AtualizarMedico {

    private final MedicoRepository medicoRepository;

    @Transactional
    public Medico executar(Medico medico) {
        Medico medicoReference = medicoRepository.getReferenceById(medico.getId());
        if (medicoReference == null) {
            throw new ResourceNotFoundException(String.format("Id %s informado não existe!", medicoReference.getId()));
        }
        return MedicoParser.validarMedicoToUpdate(medico, medicoReference);
    }


}
