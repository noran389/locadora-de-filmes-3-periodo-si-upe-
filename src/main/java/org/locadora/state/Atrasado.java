package org.locadora.state;


import org.locadora.model.Item;

public class Atrasado implements EstadoItem {
    @Override
    public void alugar(Item item) {
        System.out.println("O item está atrasado; não pode ser alugado até regularizar.");
    }

    @Override
    public void devolver(Item item) {
        System.out.println(item.getNome() + " foi devolvido com atraso. (Multas a implementar)");
        item.setEstado(new Disponivel());
    }
}