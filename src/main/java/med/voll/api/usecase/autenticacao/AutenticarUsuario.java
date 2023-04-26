package med.voll.api.usecase.autenticacao;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Usuario;
import med.voll.api.usecase.service.TokenApiService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutenticarUsuario {

    private final AuthenticationManager authenticationManager;
    private final TokenApiService tokenApiService;

    public String executar(Authentication authentication) {
        Authentication authenticate = authenticationManager.authenticate(authentication);
        return tokenApiService.generateToken((Usuario) authenticate.getPrincipal());
    }
}
