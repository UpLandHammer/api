package med.voll.api.gateway.mysql.entity.repository;

import med.voll.api.gateway.mysql.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<UserDetails> findByLogin(String login);
}
