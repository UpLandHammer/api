package med.voll.api.http.controller;

import med.voll.api.http.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.http.domain.enums.Especialidade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AgendamentoConsultaDTO> request;

    @Autowired
    private JacksonTester<AgendamentoConsultaDTO> response;

    @MockBean
    private ConsultaController consultaController;


    @Test
    @WithMockUser
    @DisplayName("Deve devolver erro 400 informações incorretas")
    void agendar_DevolveErro() throws Exception {
        MockHttpServletResponse responseMock = mockMvc.perform(post("/consultas"))
                .andReturn()
                .getResponse();
        Assertions.assertEquals(responseMock.getStatus(), HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @WithMockUser
    @DisplayName("Deve devolver erro 400 informações incorretas")
    void agendar_DevolveSucesso() throws Exception {

        LocalDateTime localDateTime = LocalDateTime.now().plusHours(2);
        Mockito.when(consultaController.agendar(Mockito.any())).thenReturn(buildConsultaAgendada(localDateTime));


        MockHttpServletResponse responseMock = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                request.write(AgendamentoConsultaDTO.builder()
                                                .especialidade(Especialidade.ORTOPEDIA)
                                                .data(localDateTime)
                                                .idPaciente(1L)
                                                .idMedico(2L)
                                        .build()).getJson()
                        ))
                .andReturn()
                .getResponse();

        String jsonResponse = response.write(
                AgendamentoConsultaDTO.builder()
                        .especialidade(Especialidade.ORTOPEDIA)
                        .data(localDateTime)
                        .idPaciente(1L)
                        .idMedico(2L)
                        .build()
        ).getJson();

        Assertions.assertEquals(responseMock.getContentAsString(), jsonResponse);
        Assertions.assertEquals(responseMock.getStatus(), HttpStatus.OK.value());


    }

    private ResponseEntity<AgendamentoConsultaDTO> buildConsultaAgendada(LocalDateTime localDateTime) {

        return ResponseEntity.ok(AgendamentoConsultaDTO.builder()
                .especialidade(Especialidade.ORTOPEDIA)
                .data(localDateTime)
                .idPaciente(1L)
                .idMedico(2L)
                .build());

    }

}