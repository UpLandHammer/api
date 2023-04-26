package med.voll.api.gateway.mysql.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.http.domain.enums.Especialidade;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicos")
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;
}
