package org.locadora.strategy;

public class CalculoLancamento implements CalculoPreco {
    @Override
    public double calcularPreco(int dias) {
        return dias * 10.0 + 5.0; // Exemplo: R$10/dia + taxa fixa
    }
}