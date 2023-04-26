package med.voll.api.http.domain.parser;

import lombok.experimental.UtilityClass;
import med.voll.api.gateway.mysql.entity.Medico;

@UtilityClass
public class MedicoParser {

    public static Medico validarMedicoToUpdate(Medico medico, Medico medicoReference) {
        if(medico.getNome() != null) {
            medicoReference.setNome(medico.getNome());
        }

        if(medico.getTelefone() != null) {
            medicoReference.setTelefone(medico.getTelefone());
        }

        if(medico.getEndereco() != null) {
            validEndereco(medico, medicoReference);
        }

        return medicoReference;
    }

    public static void validEndereco(Medico medico, Medico medicoReference) {
        if(medico.getEndereco().getLogradouro() != null) {
            medicoReference.getEndereco().setLogradouro(medico.getEndereco().getLogradouro());
        }

        if(medico.getEndereco().getNumero() != null) {
            medicoReference.getEndereco().setNumero(medico.getEndereco().getNumero());
        }

        if(medico.getEndereco().getComplemento() != null) {
            medicoReference.getEndereco().setComplemento(medico.getEndereco().getComplemento());
        }

        if(medico.getEndereco().getBairro() != null) {
            medicoReference.getEndereco().setBairro(medico.getEndereco().getBairro());
        }

        if(medico.getEndereco().getCidade() != null) {
            medicoReference.getEndereco().setLogradouro(medico.getEndereco().getCidade());
        }

        if(medico.getEndereco().getUf() != null) {
            medicoReference.getEndereco().setUf(medico.getEndereco().getUf());
        }

        if(medico.getEndereco().getCep() != null) {
            medicoReference.getEndereco().setCep(medico.getEndereco().getCep());
        }
    }

}
