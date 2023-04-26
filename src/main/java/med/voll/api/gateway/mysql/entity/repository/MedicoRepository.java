package med.voll.api.gateway.mysql.entity.repository;

import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.http.domain.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select m 
              from Medico m
             where m.ativo = 1
               and m.especialidade = :especialidade
               and m.id not in (
                    select c.medico.id
                      from Consulta c
                     where c.data = :data
               )
               order by rand()
               limit 1
            """)
    Medico escolherMedicoLivreAleatoriamenteNaData(@Param("especialidade") Especialidade especialidade, @Param("data") LocalDateTime data);

    Optional<Boolean> findAtivoById(Long id);
}
