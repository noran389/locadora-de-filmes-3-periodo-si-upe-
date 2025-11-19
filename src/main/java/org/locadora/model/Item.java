package org.locadora.model;


import org.locadora.state.Disponivel;
import org.locadora.state.EstadoItem;
import org.locadora.strategy.CalculoPreco;

public class Item {
    private String nome;
    private CalculoPreco estrategiaPreco;
    private EstadoItem estado;

    public Item(String nome, CalculoPreco estrategiaPreco) {
        this.nome = nome;
        this.estrategiaPreco = estrategiaPreco;
        this.estado = new Disponivel(); // começa disponível
    }

    public void alugar() {
        estado.alugar(this);
    }

    public void devolver() {
        estado.devolver(this);
    }

    public double calcularPreco(int dias) {
        return estrategiaPreco.calcularPreco(dias);
    }

    public String getNome() {
        return nome;
    }

    public void setEstado(EstadoItem estado) {
        this.estado = estado;
    }


    public boolean estaDisponivel() {
        return estado instanceof Disponivel;
    }

    @Override
    public String toString() {
        return nome;
    }
}