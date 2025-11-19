package org.locadora.state;


import org.locadora.model.Item;

public interface EstadoItem {
    void alugar(Item item);
    void devolver(Item item);
}