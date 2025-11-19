package org.locadora.state;


import org.locadora.model.Item;

public class Disponivel implements EstadoItem {
    @Override
    public void alugar(Item item) {
        System.out.println(item.getNome() + " foi alugado!");
        item.setEstado(new Alugado());
    }

    @Override
    public void devolver(Item item) {
        System.out.println("O item já está disponível.");
    }
}