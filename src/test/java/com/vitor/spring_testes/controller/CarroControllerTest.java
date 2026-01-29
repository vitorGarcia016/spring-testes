package com.vitor.spring_testes.controller;

import com.vitor.spring_testes.entity.CarroEntity;
import com.vitor.spring_testes.service.CarroService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CarroService carroService;

    @Autowired
    ObjectMapper objectMapper;

    CarroEntity carro;

    @BeforeEach
    void setUp(){
        carro = new CarroEntity("Honda City", 100.0,2025);
    }

    @Test
    void sucessoAoSalvarUmCarro() throws Exception {

        Integer idCarro = 1;

        CarroEntity carroSalvo = new CarroEntity(carro.getNome(), carro.getPreco(), carro.getAno());
        carroSalvo.setId(idCarro);

        when(carroService.salvarCarro(Mockito.any())).thenReturn(carroSalvo);

        String json = objectMapper.writeValueAsString(carro);
        String url = "/carro";

        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)

        );

        result
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.endsWith("carro/" + carroSalvo.getId())));

    }

    @Test
    @DisplayName("Deve Lancar Uma Exception Ao Salvar Um Carro Com Preco Menor Que Zero")
    void deveLancarUmaExceptionAoSalvarUmCarroComPrecoMenorQueZero() throws Exception {

        when(carroService.salvarCarro(any())).thenThrow(new IllegalArgumentException("Carro nao pode ter preco negativo"));


        String json = objectMapper.writeValueAsString(carro);
        String url = "/carro";

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Carro nao pode ter preco negativo"));

    }


}
