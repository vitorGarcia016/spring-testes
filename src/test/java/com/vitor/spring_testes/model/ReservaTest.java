package com.vitor.spring_testes.model;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservaTest {

    Carro carro;
    Cliente cliente;

    @BeforeEach
    void setUp() {
        carro = new Carro("CITY", 100.0);
        cliente = new Cliente("Vitor");
    }

    @Test
    void sucessoAoCriarReserva(){

        int quantidadeDias = 5;


        Reserva reserva = new Reserva(carro,cliente,quantidadeDias);

        assertThat(reserva).isNotNull();

    }




    @Test
    void sucessoAoRealizarReserva() {

        int quantidadeDias = 5;

        Reserva reserva = new Reserva(carro, cliente, quantidadeDias);

        double valor = reserva.valorDaReserva();

        assertThat(valor).isEqualTo(450.0);
    }

    @Test
    void deveLancarReservaInvalidaException() {

        assertThrows(ReservaInvalidaException.class, () -> new Reserva(carro, cliente, 0));
        assertDoesNotThrow(() -> new Reserva(carro,cliente,1));

       var erro = catchThrowable(() -> new Reserva(carro, cliente, 0));

       assertThat(erro).isInstanceOf(ReservaInvalidaException.class).hasMessage("Reserva invalida");
    }


}
