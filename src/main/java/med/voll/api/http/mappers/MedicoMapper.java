package med.voll.api.http.mappers;

import lombok.experimental.UtilityClass;
import med.voll.api.gateway.mysql.entity.Medico;
import med.voll.api.http.domain.medico.AtualizarDadosMedicoDTO;
import med.voll.api.http.domain.medico.DadosBasicosMedicoDTO;
import med.voll.api.http.domain.medico.MedicoDTO;
import med.voll.api.http.domain.response.MedicoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@UtilityClass
public class MedicoMapper {

    public static Medico dtoToModel(MedicoDTO medico) {
        return Medico.builder()
                .email(medico.getEmail())
                .nome(medico.getNome())
                .crm(medico.getCrm())
                .telefone(medico.getTelefone())
                .especialidade(medico.getEspecialidade())
                .ativo(true)
                .endereco(EnderecoMapper.dtoToModel(medico.getEndereco()))
                .build();
    }



    private static DadosBasicosMedicoDTO modelToDTO(Medico medico) {
        return DadosBasicosMedicoDTO.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .email(medico.getEmail())
                .crm(medico.getCrm())
                .especialidade(medico.getEspecialidade())
                .build();
    }

    public static Page<DadosBasicosMedicoDTO> modelsToDTOList(Page<Medico> medicosPage) {
        return new PageImpl<>(medicosPage.stream().map(MedicoMapper::modelToDTO).collect(Collectors.toList()));
    }

    public static Medico dadosAtualizacaoToModel(AtualizarDadosMedicoDTO atualizarDadosMedicoDTO) {
        return Medico.builder()
                .id(atualizarDadosMedicoDTO.getId())
                .nome(atualizarDadosMedicoDTO.getNome())
                .telefone(atualizarDadosMedicoDTO.getTelefone())
                .build();
    }

    public static MedicoResponse modelToResponse(Medico medico) {
        return MedicoResponse.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .email(medico.getEmail())
                .telefone(medico.getTelefone())
                .crm(medico.getCrm())
                .ativo(medico.isAtivo())
                .especialidade(medico.getEspecialidade())
                .endereco(EnderecoMapper.modelToResponse(medico.getEndereco()))
                .build();
    }


}
