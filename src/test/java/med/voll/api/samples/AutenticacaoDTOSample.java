package med.voll.api.samples;

import lombok.experimental.UtilityClass;
import med.voll.api.http.domain.usuario.AutenticacaoDTO;

@UtilityClass
public class AutenticacaoDTOSample {

    public static AutenticacaoDTO create() {
        AutenticacaoDTO autenticacaoDTO = new AutenticacaoDTO();
        autenticacaoDTO.setLogin("user@user.com.br");
        autenticacaoDTO.setSenha("123456");
        return autenticacaoDTO;
    }
}
