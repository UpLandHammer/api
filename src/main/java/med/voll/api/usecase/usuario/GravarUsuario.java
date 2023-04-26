package med.voll.api.usecase.usuario;

import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Usuario;
import med.voll.api.gateway.mysql.entity.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GravarUsuario {

    private final UsuarioRepository usuarioRepository;

    public void executar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
