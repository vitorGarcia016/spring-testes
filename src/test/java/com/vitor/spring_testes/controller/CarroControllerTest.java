package com.vitor.spring_testes.controller;

import com.vitor.spring_testes.entity.CarroEntity;
import com.vitor.spring_testes.service.CarroService;
import jakarta.persistence.EntityNotFoundException;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)

        );

        result
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.endsWith("carro/" + carroSalvo.getId())));

    }

    @Test
    @DisplayName("Deve Lancar Uma Exception Ao Salvar Um Carro Com Preco Menor Que Zero")
    void deveLancarUmaExceptionAoSalvarUmCarroComPrecoMenorQueZero() throws Exception {

        when(carroService.salvarCarro(any())).thenThrow(new IllegalArgumentException("Carro nao pode ter preco negativo"));


        String json = objectMapper.writeValueAsString(carro);
        String url = "/carro";

        ResultActions resultActions = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Carro nao pode ter preco negativo"));

    }

    @Test
    @DisplayName("Sucesso ao tentar buscar por id um carro")
    void sucessoAoBuscarCarroPorId() throws Exception{

        Integer idCarro = 1;

        CarroEntity carroSalvo = new CarroEntity(carro.getNome(), carro.getPreco(), carro.getAno());
        carroSalvo.setId(idCarro);

        when(carroService.buscarPorId(Mockito.any())).thenReturn(carroSalvo);

        ResultActions resultActions = mockMvc.perform(get("/carro/1"));


        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(carroSalvo.getId()))
                .andExpect(jsonPath("$.nome").value(carroSalvo.getNome()))
                .andExpect(jsonPath("$.preco").value(carroSalvo.getPreco()))
                .andExpect(jsonPath("$.ano").value(carroSalvo.getAno()));
    }

    @Test
    void deveLancarUmaExceptionAoTentarBuscarUmCarroInexistente() throws Exception {
        when(carroService.buscarPorId(any())).thenThrow(new EntityNotFoundException("Carro não encontrado"));

        ResultActions resultActions = mockMvc.perform(get("/carro/1"));

        resultActions
                .andExpect(status().isNotFound())
                .andExpect(content().string("Carro não encontrado"));
    }


    @Test
    void sucessoAoBuscarTodosOsCarros() throws Exception {

        List<CarroEntity> carros = List.of(
                new CarroEntity("HB20",200.0,2010),
                new CarroEntity("Fiat",100.0,1980)
        );

        when(carroService.carroEntities()).thenReturn(carros);

        ResultActions resultActions = mockMvc.perform(
                get("/carro")
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(carros.get(0).getNome()))
                .andExpect(jsonPath("$[1].nome").value(carros.get(1).getNome()));

    }


}
