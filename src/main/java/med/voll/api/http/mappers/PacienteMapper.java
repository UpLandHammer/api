package med.voll.api.http.mappers;

import lombok.experimental.UtilityClass;
import med.voll.api.gateway.mysql.entity.Paciente;
import med.voll.api.http.domain.paciente.DadosBasicosPacienteDTO;
import med.voll.api.http.domain.paciente.PacienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@UtilityClass
public class PacienteMapper {

    public static Paciente fromDTO(PacienteDTO pacienteDTO) {

        return Paciente.builder()
                .nome(pacienteDTO.getNome())
                .email(pacienteDTO.getEmail())
                .cpf(pacienteDTO.getCpf())
                .telefone(pacienteDTO.getTelefone())
                .endereco(EnderecoMapper.dtoToModel(pacienteDTO.getEndereco()))
                .build();
    }

    private static DadosBasicosPacienteDTO dadosBasicosFromPaciente(Paciente paciente) {
        return DadosBasicosPacienteDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .email(paciente.getEmail())
                .build();
    }

    public static Page<DadosBasicosPacienteDTO> dadosBasicosFromPacientes(Page<Paciente> pacientes) {
        return new PageImpl<>(pacientes.stream().map(PacienteMapper::dadosBasicosFromPaciente).collect(Collectors.toList()));
    }
}
