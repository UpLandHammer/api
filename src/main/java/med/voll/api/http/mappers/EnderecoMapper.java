package med.voll.api.http.mappers;

import lombok.experimental.UtilityClass;
import med.voll.api.gateway.mysql.entity.Endereco;
import med.voll.api.http.domain.endereco.DadosEnderecoDTO;
import med.voll.api.http.domain.response.EnderecoResponse;

@UtilityClass
public class EnderecoMapper {

    public static Endereco dtoToModel(DadosEnderecoDTO endereco) {
        return Endereco.builder()
                .uf(endereco.getUf())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .logradouro(endereco.getLogradouro())
                .complemento(endereco.getComplemento())
                .build();
    }

    public static DadosEnderecoDTO modelToDTO(Endereco endereco) {
        return DadosEnderecoDTO.builder()
                .uf(endereco.getUf())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .logradouro(endereco.getLogradouro())
                .complemento(endereco.getComplemento())
                .build();
    }

    public static EnderecoResponse modelToResponse(Endereco endereco) {
        return EnderecoResponse.builder()
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .cep(endereco.getCep())
                .uf(endereco.getUf())
                .build();
    }

}
