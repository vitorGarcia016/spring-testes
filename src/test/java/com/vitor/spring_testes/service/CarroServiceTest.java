package com.vitor.spring_testes.service;

import com.vitor.spring_testes.entity.CarroEntity;
import com.vitor.spring_testes.repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

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

        var erro = catchThrowable(() -> carroService.salvarCarro(carro));

        assertThat(erro)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Preço nao pode ser zero");

        verify(carroRepository, never()).save(any());

    }

    @Test
    @DisplayName("Sucesso ao tentar atualizar um carro")
    void sucessoAoAtualizarCarro() {

        CarroEntity carroAtualizado = new CarroEntity("New Fiesta", 60.0, 2015);

        when(carroRepository.findById(any())).thenReturn(Optional.of(carro));


        CarroEntity respostaMetodo = carroService.atualizarCarro(1, carroAtualizado);

        assertThat(respostaMetodo.getNome()).isEqualTo(carroAtualizado.getNome());
        assertThat(respostaMetodo.getPreco()).isEqualTo(carroAtualizado.getPreco());
        verify(carroRepository).save(any());

    }


    @Test
    @DisplayName("Deve lançar uma exception ao tentar atualizar um carro não existente")
    void deveDaErroAoTentarAtualizarUmCarroNaoExistente() {

        Integer id = 1;

        when(carroRepository.findById(any())).thenReturn(Optional.empty());


        Throwable erro = catchThrowable(() -> carroService.atualizarCarro(id, carro));

        assertThat(erro).hasMessage("Carro não encontrado").isInstanceOf(EntityNotFoundException.class);
        verify(carroRepository, never()).save(any());


    }

    @Test
    @DisplayName("Sucesso ao deletar um carro")
    void sucessoAodeletarUmcarro(){

        Integer idCarro = 1;

        when(carroRepository.findById(any())).thenReturn(Optional.of(carro));

        carroService.deletarCarro(idCarro);

        verify(carroRepository).delete(carro);

    }

    @Test
    @DisplayName("Deve lançar uma exception ao tentar deletar um carro não existente")
    void deveDaErroAoTentarDeletarUmCarroInexistente(){

        Integer idCarro = 1;
        when(carroRepository.findById(any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.deletarCarro(idCarro));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class).hasMessage("Carro não encontrado");
        verify(carroRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Sucesso ao buscar um carro por id")
    void sucessoAoBuscarPorId(){

        Integer idCarro = 1;

        when(carroRepository.findById(idCarro)).thenReturn(Optional.of(carro));

        CarroEntity respostaMetodo = carroService.buscarPorId(idCarro);

        assertThat(respostaMetodo).isNotNull();
        assertThat(respostaMetodo.getNome()).isEqualTo(carro.getNome());

    }

    @Test
    void deveDaErroAoBuscarUmCarroInexistente(){
        when(carroRepository.findById(any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> carroService.buscarPorId(any()));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void sucessoAoBuscarTodosOsCarros(){
        when(carroRepository.findAll()).thenReturn(List.of(carro));

        List<CarroEntity> respostaMetodo = carroService.carroEntities();

        assertThat(respostaMetodo).isNotNull();
        assertThat(respostaMetodo.get(0)).isInstanceOf(CarroEntity.class);

    }

}