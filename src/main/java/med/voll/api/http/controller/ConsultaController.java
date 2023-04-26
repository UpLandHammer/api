package med.voll.api.http.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Consulta;
import med.voll.api.http.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.http.mappers.ConsultaMapper;
import med.voll.api.usecase.consulta.AgendarConsultas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final AgendarConsultas agendarConsultas;

    @PostMapping
    public ResponseEntity<AgendamentoConsultaDTO> agendar(@RequestBody @Valid AgendamentoConsultaDTO agendamentoConsultaDTO) {

        Consulta consulta = agendarConsultas.executar(ConsultaMapper.from(agendamentoConsultaDTO));

        return ResponseEntity.ok().body(ConsultaMapper.from(consulta));
    }

}
