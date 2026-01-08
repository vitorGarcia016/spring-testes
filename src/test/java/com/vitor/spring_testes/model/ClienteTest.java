package com.vitor.spring_testes.model;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    @DisplayName("Sucesso ao instanciar um cliente")
    void SucessoAoCriarCliente(){
        Cliente cliente = new Cliente("Vitor");

        String nome = cliente.getNome();

        assertNotNull(nome);
        assertThat(nome).isEqualTo("Vitor");
    }

}
