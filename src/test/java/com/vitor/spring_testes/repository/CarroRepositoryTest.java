package com.vitor.spring_testes.repository;

import com.vitor.spring_testes.entity.CarroEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("testes")
class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    private CarroEntity entity;

    @BeforeEach
    void setUp() {
        entity = new CarroEntity("City", 100.0);
    }

    @Test
    void deveSalvarCarro() {

        carroRepository.save(entity);

        assertThat(entity.getId()).isNotNull();


    }

    @Test
    @Sql("/sql/Povoando-BD.sql")
    void deveBuscarCarroPorNome(){

        CarroEntity city = carroRepository.findByNome("City");


        assertThat(city).isNotNull();
        assertThat(city.getNome()).isEqualTo("City");
        assertThat(city.getPreco()).isEqualTo(100.0);

    }

}