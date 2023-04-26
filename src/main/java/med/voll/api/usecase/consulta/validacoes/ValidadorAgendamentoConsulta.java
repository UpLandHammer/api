package med.voll.api.usecase.consulta.validacoes;

import med.voll.api.gateway.mysql.entity.Consulta;

public interface ValidadorAgendamentoConsulta {

    void validar(Consulta consulta);
}
