package org.locadora.model;


import org.locadora.observer.Cliente;
import org.locadora.observer.Notificacao;

public class Aluguel {
    private Item item;
    private Cliente cliente;
    private int dias;
    private double valor;
    private Notificacao notificacao;

    public Aluguel(Item item, Cliente cliente, int dias, Notificacao notificacao) {
        this.item = item;
        this.cliente = cliente;
        this.dias = dias;
        this.notificacao = notificacao;
        this.valor = item.calcularPreco(dias);

        if (this.notificacao != null) {
            this.notificacao.adicionarObservador(cliente);
        }
    }

    public Item getItem() {
        return item;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getDias() {
        return dias;
    }

    public double getValor() {
        return valor;
    }

    public void realizarAluguel() {
        item.alugar();
        if (notificacao != null) {
            notificacao.notificar(cliente, "Aluguel confirmado: '" + item.getNome() + "' por " + dias + " dias. Valor: R$ " + valor);
        }
    }

    public void devolver() {
        item.devolver();
        if (notificacao != null) {
            notificacao.notificar(cliente, "Devolução registrada: '" + item.getNome() + "'. Obrigado!");
            // opcional: remover das notificações se não quiser mais notificar esse cliente
            notificacao.removerObservador(cliente);
        }
    }

    @Override
    public String toString() {
        return item.getNome() + " | Cliente: " + cliente.getNome() + " | Dias: " + dias + " | Valor: R$ " + valor;
    }
}