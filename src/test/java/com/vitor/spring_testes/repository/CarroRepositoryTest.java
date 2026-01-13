package com.vitor.spring_testes.repository;

import com.vitor.spring_testes.entity.CarroEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("testes")
class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    private CarroEntity entity;

    @BeforeEach
    void setUp(){
        entity = new CarroEntity("City",100.0);
    }

    @Test
    void deveSalvarCarro(){

        carroRepository.save(entity);

        assertThat(entity.getId()).isNotNull();


    }

}