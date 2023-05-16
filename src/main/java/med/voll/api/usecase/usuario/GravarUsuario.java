package med.voll.api.usecase.usuario;

import lombok.RequiredArgsConstructor;
import med.voll.api.exceptions.UsuarioException;
import med.voll.api.gateway.mysql.entity.Usuario;
import med.voll.api.gateway.mysql.entity.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GravarUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuscarUsuarioPorEmail buscarUsuarioPorEmail;

    public void executar(Usuario usuario) {

        if (buscarUsuarioPorEmail.execute(usuario.getLogin())){
            throw new UsuarioException("Usuário já existe");
        }

        String senha = usuario.getSenha();
        String encode = passwordEncoder.encode(senha);
        usuario.setSenha(encode);

        usuarioRepository.save(usuario);
    }
}
