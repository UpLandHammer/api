package med.voll.api.config.security.interceptador;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import med.voll.api.usecase.autenticacao.AdicionarAutenticacaoUsuario;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ApiSecurityFilter extends OncePerRequestFilter {

    private final AdicionarAutenticacaoUsuario adicionarAutenticacaoUsuario;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJWT = retrieveToken(request);

        if (!Objects.isNull(tokenJWT)) {
            adicionarAutenticacaoUsuario.executar(tokenJWT);
        }

        filterChain.doFilter(request, response);
    }

    private String retrieveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (!Objects.isNull(authorizationHeader)) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;

    }
}
