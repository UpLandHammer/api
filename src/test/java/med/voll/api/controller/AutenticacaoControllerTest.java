package med.voll.api.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import med.voll.api.http.controller.AutenticacaoController;
import med.voll.api.http.domain.security.TokenAuthenticatedReponse;
import med.voll.api.http.domain.usuario.AutenticacaoDTO;
import med.voll.api.samples.AutenticacaoDTOSample;
import med.voll.api.usecase.autenticacao.AutenticarUsuario;
import med.voll.api.usecase.usuario.GravarUsuario;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacaoControllerTest {

    @Mock
    private GravarUsuario gravarUsuario;
    @Mock
    private AutenticarUsuario autenticarUsuario;

    @InjectMocks
    private AutenticacaoController autenticacaoController;

    private static final String JWT_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlVzZXIiLCJhZG1pbiI6dHJ1ZSwiaWF0IjoxNjgxOTUzMTQ4LCJleHAiOjE2ODE5NTY3NDh9.GBnlJtbZIarMsBMRoeDJdYh0pFIapAPhan49x8TjCPg";

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("Deve Gerar token")
    void login() {

        AutenticacaoDTO autenticacaoDTO = AutenticacaoDTOSample.create();
        when(autenticarUsuario.executar(any())).thenReturn(JWT_TOKEN);
        ResponseEntity<TokenAuthenticatedReponse> login = autenticacaoController.login(autenticacaoDTO);

        TokenAuthenticatedReponse tokenAuthenticatedReponse = login.getBody();
        Assertions.assertTrue(JWT_TOKEN.equals(tokenAuthenticatedReponse.getToken()));
    }


    @Test
    @DisplayName("Deve gerar erro ao tentar logar")
    void erroNoLoginSemSenhaNoRequest() {
        AutenticacaoDTO autenticacaoDTO = AutenticacaoDTOSample.create();
        autenticacaoDTO.setSenha(null);
        this.validator.validate(autenticacaoDTO);
        autenticacaoController.login(autenticacaoDTO);
    }
}
