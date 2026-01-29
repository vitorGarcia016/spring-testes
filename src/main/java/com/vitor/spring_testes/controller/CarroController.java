package com.vitor.spring_testes.controller;

import com.vitor.spring_testes.entity.CarroEntity;
import com.vitor.spring_testes.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<CarroEntity> buscarPorId(@PathVariable Integer id){
        CarroEntity carroEntity = carroService.buscarPorId(id);

        return ResponseEntity.ok().body(carroEntity);
    }

    @GetMapping
    public ResponseEntity<List<CarroEntity>> buscarTodos(){
        List<CarroEntity> carroEntities = carroService.carroEntities();

        return ResponseEntity.ok().body(carroEntities);
    }
}
