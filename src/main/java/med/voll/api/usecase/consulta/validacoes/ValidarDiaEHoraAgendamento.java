package med.voll.api.usecase.consulta.validacoes;

import med.voll.api.exceptions.AgendamentoConsultasException;
import med.voll.api.gateway.mysql.entity.Consulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarDiaEHoraAgendamento implements ValidadorAgendamentoConsulta {

    public void validar(Consulta consulta) {
        boolean isDomingo = consulta.getData().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        int horaDesejadaConsulta = consulta.getData().getHour();

        if (isDomingo || horaDesejadaConsulta < 7 || horaDesejadaConsulta > 18) {
            throw new AgendamentoConsultasException("Horário solicitado fora do expediente");
        }
    }
}
