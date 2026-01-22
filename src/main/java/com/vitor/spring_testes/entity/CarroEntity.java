package com.vitor.spring_testes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carro")
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Double preco;

    private int ano;


    public CarroEntity(String nome, Double preco, int ano) {
        this.nome = nome;
        this.preco = preco;
        this.ano = ano;
    }

    public CarroEntity() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
