package med.voll.api.gateway.mysql.entity.repository;

import med.voll.api.gateway.mysql.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
