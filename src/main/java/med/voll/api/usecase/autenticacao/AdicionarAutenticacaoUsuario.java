package med.voll.api.usecase.autenticacao;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.repository.UsuarioRepository;
import med.voll.api.usecase.service.TokenApiService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdicionarAutenticacaoUsuario {

    private final UsuarioRepository usuarioRepository;
    private final TokenApiService tokenApiService;

    public void executar(String tokenJWT) {
        String subject = tokenApiService.getSubject(tokenJWT);

        UserDetails usuario = usuarioRepository.findByLogin(subject);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
