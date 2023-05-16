package med.voll.api.usecase.usuario;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BuscarUsuarioPorEmail {

    private final UsuarioRepository usuarioRepository;

    public boolean execute(String email) {
        return !Objects.isNull(usuarioRepository.findByLogin(email).orElse(null));

    }
}
