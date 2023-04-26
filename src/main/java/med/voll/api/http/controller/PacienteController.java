package med.voll.api.http.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.http.domain.paciente.DadosBasicosPacienteDTO;
import med.voll.api.http.domain.paciente.PacienteDTO;
import med.voll.api.http.mappers.PacienteMapper;
import med.voll.api.usecase.paciente.BuscarPacientes;
import med.voll.api.usecase.paciente.GravarPaciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    private final GravarPaciente gravarPaciente;
    private final BuscarPacientes buscarPacientes;

    @PostMapping
    public void cadastrarPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        gravarPaciente.executar(PacienteMapper.fromDTO(pacienteDTO));
    }

    @GetMapping
    public ResponseEntity<Page<DadosBasicosPacienteDTO>> list(@PageableDefault( sort = {"nome"}, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(PacienteMapper.dadosBasicosFromPacientes(buscarPacientes.executar(pageable)));
    }
}
