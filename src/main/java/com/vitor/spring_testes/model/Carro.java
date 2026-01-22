package com.vitor.spring_testes.model;

public class Carro {

    private String nome;
    private double valorDiario;
    private int ano;


    public Carro(String nome, double valorDiario, int ano) {
        this.nome = nome;
        this.valorDiario = valorDiario;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(double valorDiario) {
        this.valorDiario = valorDiario;
    }


    public double valorAluguel(int dias){

        return dias >= 5 ? (dias * valorDiario) - 50: dias * valorDiario;
    }
}
