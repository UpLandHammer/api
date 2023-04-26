package med.voll.api.http.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.http.domain.medico.AtualizarDadosMedicoDTO;
import med.voll.api.http.domain.medico.DadosBasicosMedicoDTO;
import med.voll.api.http.domain.medico.MedicoDTO;
import med.voll.api.http.mappers.MedicoMapper;
import med.voll.api.http.domain.response.MedicoResponse;
import med.voll.api.usecase.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {


    public static final String API_ROOT_MEDICOS = "/medicos";
    public static final String ID = "/{id}";
    public static final String MEDICOS_ID = API_ROOT_MEDICOS + ID;
    private final GravarMedico gravarMedico;
    private final BuscarMedicos buscarMedicos;
    private final AtualizarMedico atualizarMedico;
    private final InativarMedico inativarMedico;
    private final BuscarMedicoPorId buscarMedicoPorId;
    @PostMapping
    public ResponseEntity<MedicoResponse> cadastrar(@Valid @RequestBody MedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = gravarMedico.execute(MedicoMapper.dtoToModel(medicoDTO));
        return ResponseEntity
                .created(buildURIRegistroManipulado(uriComponentsBuilder, medico))
                .body(MedicoMapper.modelToResponse(medico));
    }

    @PutMapping
    public ResponseEntity<MedicoResponse> update(@RequestBody @Valid AtualizarDadosMedicoDTO atualizarDadosMedicoDTO, UriComponentsBuilder uriComponentsBuilder) throws URISyntaxException {
        Medico medico = atualizarMedico.executar(MedicoMapper.dadosAtualizacaoToModel(atualizarDadosMedicoDTO));
        return ResponseEntity
                .created(buildURIRegistroManipulado(uriComponentsBuilder, medico))
                .body(MedicoMapper.modelToResponse(medico));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity delete(@PathVariable Long id) {
        inativarMedico.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosBasicosMedicoDTO>> listar(@PageableDefault(page = 0, size = 10, sort = {"nome"}) Pageable pageable) {
        return ResponseEntity.ok().body(MedicoMapper.modelsToDTOList(buscarMedicos.executar(pageable)));
    }

    @GetMapping(value = ID)
    public ResponseEntity<MedicoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(MedicoMapper.modelToResponse(buscarMedicoPorId.executar(id)));
    }

    private static URI buildURIRegistroManipulado(UriComponentsBuilder uriComponentsBuilder, Medico medico) {
        return uriComponentsBuilder.path(MEDICOS_ID).buildAndExpand(medico.getId()).toUri();
    }
}
