package com.vitor.spring_testes.model;

public class Reserva {

    private Cliente cliente;

    private Carro carro;

    private int quantidadeDias;

    public Reserva(Carro carro, Cliente cliente, int quantidadeDias) {
        this.carro = carro;
        this.cliente = cliente;
        this.quantidadeDias = quantidadeDias;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    public double valorDaReserva(){

        if(quantidadeDias <= 0){
            throw new ReservaInvalidaException("Reserva invalida");
        }

        return carro.valorAluguel(quantidadeDias);

    }
}
