package org.locadora.state;


import org.locadora.model.Item;

public class Alugado implements EstadoItem {
    @Override
    public void alugar(Item item) {
        System.out.println("O item já está alugado e não pode ser alugado novamente.");
    }

    @Override
    public void devolver(Item item) {
        System.out.println(item.getNome() + " foi devolvido!");
        item.setEstado(new Disponivel());
    }
}