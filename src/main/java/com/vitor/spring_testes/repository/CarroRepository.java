package com.vitor.spring_testes.repository;

import com.vitor.spring_testes.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<CarroEntity,Integer> {

    CarroEntity findByNome(String nome);
}
