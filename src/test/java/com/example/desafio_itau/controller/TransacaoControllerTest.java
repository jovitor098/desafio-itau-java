package com.example.desafio_itau.controller;

import com.example.desafio_itau.entidade.Transacao;
import com.example.desafio_itau.exception.GlobalExceptionHandler;
import com.example.desafio_itau.service.TransacaoService;
import com.example.desafio_itau.service.exceptions.UnprocessableEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(transacaoController)
                .setControllerAdvice(GlobalExceptionHandler.class)
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

    }

    @Test
    void criarTransacaoCorreta() throws Exception {
        Transacao transacao = new Transacao(10.0, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);
        mockMvc.perform(
                post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void criarTransacaoIncorreta() throws Exception {
        doThrow(new UnprocessableEntity("Erro ao processar a requisicao"))
                .when(transacaoService)
                .addTransacao(any());
        Transacao transacao = new Transacao(-10.0, OffsetDateTime.now());
        String json = objectMapper.writeValueAsString(transacao);
        mockMvc.perform(
                       post("/transacao")
                               .contentType(MediaType.APPLICATION_JSON)
                               .content(json))
               .andExpect(status().isUnprocessableEntity());
    }
}