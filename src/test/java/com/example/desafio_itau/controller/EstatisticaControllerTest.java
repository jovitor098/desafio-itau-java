package com.example.desafio_itau.controller;

import com.example.desafio_itau.entidade.Estatistica;
import com.example.desafio_itau.service.EstatisticaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EstatisticaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private EstatisticaService estatisticaService;

    @InjectMocks
    private EstatisticaController estatisticaController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
    }

    @Test
    void testaControllerSemParametroTempo() throws Exception {
        Estatistica estatistica = new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);
        when(estatisticaService.obterEstatisticas(60)).thenReturn(estatistica);
        mockMvc.perform(get("/estatistica"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(0L));
    }

    @Test
    void testaControllerComParametroTempo() throws Exception {
        Estatistica estatistica = new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);
        when(estatisticaService.obterEstatisticas(120)).thenReturn(estatistica);
        mockMvc.perform(get("/estatistica")
                       .param("tempo", "120"))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(0L));
    }
}