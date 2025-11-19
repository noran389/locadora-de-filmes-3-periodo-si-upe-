package org.locadora.observer;

import java.util.ArrayList;
import java.util.List;

public class Notificacao {
    private final List<Observador> observadores = new ArrayList<>();

    public void adicionarObservador(Observador o) {
        if (o != null && !observadores.contains(o)) {
            observadores.add(o);
        }
    }

    public void removerObservador(Observador o) {
        observadores.remove(o);
    }

    public void notificarTodos(String mensagem) {
        for (Observador o : new ArrayList<>(observadores)) {
            o.atualizar(mensagem);
        }
    }

    public void notificar(Observador o, String mensagem) {
        if (o != null) {
            o.atualizar(mensagem);
        }
    }
}