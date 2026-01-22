package com.vitor.spring_testes.service;

import com.vitor.spring_testes.entity.CarroEntity;
import com.vitor.spring_testes.repository.CarroRepository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    private CarroService carroService;

    @Mock
    private CarroRepository carroRepository;

    private CarroEntity carro;

    @BeforeEach
    void setUp() {
        carro = new CarroEntity("City", 100.0, 2025);
    }

    @Test
    @DisplayName("Sucesso ao salvar um carro no banco de dados")
    void deveSalvarUmCarro() {

        when(carroRepository.save(any())).thenReturn(carro);

        CarroEntity carroEntity = carroService.salvarCarro(carro);

        verify(carroRepository).save(any());
        assertThat(carroEntity).isNotNull();
        assertThat(carroEntity.getNome()).isEqualTo(carro.getNome());

    }

    @Test
    void deveDarErroAoSalvarCarroComPrecoMenorOuIgualAZero() {

        carro.setPreco(0.0);

        var erro =  catchThrowable(() -> carroService.salvarCarro(carro));

        assertThat(erro)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Preço nao pode ser zero");

        verify(carroRepository,never()).save(any());

    }

}