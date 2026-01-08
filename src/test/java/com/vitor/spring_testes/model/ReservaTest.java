package com.vitor.spring_testes.model;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ReservaTest {

    @Test
    void sucessoAoRealizarReserva(){

        Carro carro = new Carro("City", 100);
        Cliente cliente = new Cliente("Vitor");
        int quantidadeDias = 5;

        Reserva reserva = new Reserva(carro,cliente,quantidadeDias);

        double valor = reserva.valorDaReserva();

        assertThat(valor).isEqualTo(450.0);
    }

    @Test
    void deveLancarReservaInvalidaException(){

        Carro carro = new Carro("City", 100);
        Cliente cliente = new Cliente("Vitor");
        int quantidadeDias = 0;

        Reserva reserva = new Reserva(carro,cliente,quantidadeDias);


        assertThrows(ReservaInvalidaException.class, () -> reserva.valorDaReserva());
    }



}
