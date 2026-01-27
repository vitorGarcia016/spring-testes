package com.vitor.spring_testes.controller;

import com.vitor.spring_testes.entity.CarroEntity;
import com.vitor.spring_testes.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@RestController
@RequestMapping("carro")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CarroEntity carro){
        CarroEntity carroSalvo = carroService.salvarCarro(carro);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carroSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }
}
