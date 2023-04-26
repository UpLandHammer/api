package med.voll.api.gateway.mysql.entity.repository;

import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.gateway.mysql.entity.Endereco;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.gateway.mysql.entity.Paciente;
import med.voll.api.http.domain.enums.Especialidade;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private TestEntityManager em;
    @Test
    @DisplayName("Deve devolver null quando unico médico cadastrado não está disponível na data")
    void escolherMedicoLivreAleatoriamenteNaData() {
        LocalDateTime dataConsulta = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        Medico medico = cadastrarMedico();
        Paciente paciente = cadastrarPaciente();
        cadastrarConsulta(medico, paciente, dataConsulta);


        Medico medicoLivre = repository.escolherMedicoLivreAleatoriamenteNaData(Especialidade.DERMATOLOGIA, dataConsulta);
        Assertions.assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime dataConsulta) {
        Consulta consulta = Consulta.builder()
                .data(dataConsulta)
                .medico(medico)
                .paciente(paciente)
                .especialidade(Especialidade.DERMATOLOGIA)
                .build();

        em.persist(consulta);
    }


    private Paciente cadastrarPaciente() {

        Paciente paciente = Paciente.builder()
                .cpf("11122233344")
                .telefone("11999887766")
                .email("jose@gmail.com")
                .nome("Jose Silva")
                .endereco(createEndereco())
                .build();

        return em.persist(paciente);


    }

    private Medico cadastrarMedico() {

        Medico medico = Medico.builder()
                .nome("Flavio Santos")
                .crm("998877")
                .email("flavio.santos@gmail.com")
                .ativo(true)
                .telefone("11988776655")
                .especialidade(Especialidade.DERMATOLOGIA)
                .endereco(createEndereco())
                .build();

        return em.persist(medico);

    }

    private static Endereco createEndereco() {
        return Endereco.builder()
                .uf("SP")
                .cep("11850000")
                .numero("200")
                .cidade("SAO PAULO")
                .complemento("CS 20")
                .bairro("LIBERDADE")
                .logradouro("Av Da Saudade")
                .build();
    }

}