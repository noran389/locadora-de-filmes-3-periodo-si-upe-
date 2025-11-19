package org.locadora.observer;

public class Cliente implements Observador {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("Notificação para " + nome + ": " + mensagem);
    }

    public String getNome() {
        return nome;
    }
}