package med.voll.api.http.parser;

import lombok.experimental.UtilityClass;
import med.voll.api.http.domain.usuario.AutenticacaoDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@UtilityClass
public class LoginParser {

    public static Authentication from(AutenticacaoDTO autenticacaoDTO) {
        return new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(), autenticacaoDTO.getSenha());
    }
}
