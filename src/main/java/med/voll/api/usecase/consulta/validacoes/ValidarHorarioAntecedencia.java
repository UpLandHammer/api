package med.voll.api.usecase.consulta.validacoes;

import med.voll.api.exceptions.AgendamentoConsultasException;
import med.voll.api.gateway.mysql.entity.Consulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;

@Component
public class ValidarHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    public void validar(Consulta consulta) {
        long minutes = Duration.between(LocalDate.now(), consulta.getData()).toMinutes();

        if (minutes < 30) {
            throw new AgendamentoConsultasException("Horário indisponpível");
        }
    }
}
