package org.locadora.strategy;

public class CalculoJogo implements CalculoPreco {
    @Override
    public double calcularPreco(int dias) {
        return dias * 8.0;
    }
}