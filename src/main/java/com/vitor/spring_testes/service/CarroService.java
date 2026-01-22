package com.vitor.spring_testes.service;

import com.vitor.spring_testes.entity.CarroEntity;
import com.vitor.spring_testes.repository.CarroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public CarroEntity salvarCarro(CarroEntity carro){
        if (carro.getPreco() <= 0){
            throw new IllegalArgumentException("Preço nao pode ser zero");
        }

        return carroRepository.save(carro);

    }

    public CarroEntity atualizarCarro(Integer id, CarroEntity carroAtualizado){

        CarroEntity carroExistente = buscarPorId(id);

        carroExistente.setNome(carroAtualizado.getNome());
        carroExistente.setAno(carroAtualizado.getAno());
        carroExistente.setPreco(carroAtualizado.getPreco());

        carroRepository.save(carroExistente);

        return carroExistente;

    }

    public CarroEntity buscarPorId(Integer id){
        return carroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado"));
    }

    public void deletarCarro(Integer id){
        CarroEntity carroEncontrado = buscarPorId(id);

        carroRepository.delete(carroEncontrado);
    }

    public List<CarroEntity> carroEntities(){
        return carroRepository.findAll();
    }

}
