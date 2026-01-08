package com.vitor.spring_testes.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Sucesso ao calcular valor do aluguel")
    void deveCalcularValorAluguel(){

        Carro carro = new Carro("Honda City", 100.0);
        int quantidadeDias = 3;

        double valorCobrado = carro.valorAluguel(quantidadeDias);

        Assertions.assertEquals(300.0, valorCobrado);
    }

    @Test
    @DisplayName("Sucesso ao calcular valor do aluguel com desconto")
    void deveCalcularValorAluguelComDesconto(){

        Carro carro = new Carro("Honda City", 100.0);
        int quantidadeDias = 5;

        double valorCobrado = carro.valorAluguel(quantidadeDias);

        Assertions.assertEquals(450, valorCobrado);
    }
}