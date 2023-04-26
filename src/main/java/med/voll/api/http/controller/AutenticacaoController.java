package med.voll.api.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.http.domain.security.TokenAuthenticatedReponse;
import med.voll.api.http.domain.usuario.AutenticacaoDTO;
import med.voll.api.http.domain.usuario.UsuarioDTO;
import med.voll.api.http.mappers.UsuarioMapper;
import med.voll.api.http.parser.LoginParser;
import med.voll.api.usecase.autenticacao.AutenticarUsuario;
import med.voll.api.usecase.usuario.GravarUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AutenticacaoController {

    private final GravarUsuario gravarUsuario;
    private final AutenticarUsuario autenticarUsuario;

    @PostMapping
    public ResponseEntity<TokenAuthenticatedReponse> login(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        String tokenJWT = autenticarUsuario.executar(LoginParser.from(autenticacaoDTO));
        return ResponseEntity.ok().body(TokenAuthenticatedReponse.builder().token(tokenJWT).build());
    }

    @PostMapping(value = "/create/usuario")
    public ResponseEntity createUser(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        gravarUsuario.executar(UsuarioMapper.from(usuarioDTO));
        return ResponseEntity.ok().build();
    }
}
