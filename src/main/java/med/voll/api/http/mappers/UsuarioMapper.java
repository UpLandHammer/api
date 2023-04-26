package med.voll.api.http.mappers;

import lombok.experimental.UtilityClass;
import med.voll.api.gateway.mysql.entity.Usuario;
import med.voll.api.http.domain.usuario.UsuarioDTO;

@UtilityClass
public class UsuarioMapper {

    public static Usuario from(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .login(usuarioDTO.getLogin())
                .senha(usuarioDTO.getSenha())
                .build();
    }
}
