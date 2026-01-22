package com.vitor.spring_testes.repository;

import com.vitor.spring_testes.entity.CarroEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("testes")
class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    private CarroEntity entity;

    private int idCity;

    @BeforeEach
    void setUp() {
        entity = new CarroEntity("City", 100.0, 2025);
        idCity = 1;
    }

    @Test
    void deveSalvarCarro() {

        carroRepository.save(entity);

        assertThat(entity.getId()).isNotNull();


    }

    @Test
    @Sql("/sql/Povoando-BD.sql")
    void deveBuscarCarroPorNome() {

        CarroEntity city = carroRepository.findByNome("City");


        assertThat(city).isNotNull();
        assertThat(city.getNome()).isEqualTo("City");
        assertThat(city.getPreco()).isEqualTo(100.0);

    }

    @Test
    @Sql("/sql/Povoando-BD.sql")
    void deveAtualizarAnoCarro() {

        int novoAno = 2024;

        Optional<CarroEntity> optionalCarro = carroRepository.findById(idCity);

        optionalCarro.get().setAno(novoAno);

        CarroEntity carro = carroRepository.save(optionalCarro.get());

        assertThat(carro.getAno()).isEqualTo(novoAno);


    }

    @Test
    @Sql("/sql/Povoando-BD.sql")
    void deveExcluirUmCarroPorId() {


        Optional<CarroEntity> optionalCarro = carroRepository.findById(idCity);

        carroRepository.delete(optionalCarro.get());

        Optional<CarroEntity> carroExcluido = carroRepository.findById(idCity);

        assertThat(carroExcluido).isEmpty();


    }

}