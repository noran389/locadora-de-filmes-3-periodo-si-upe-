package org.locadora.strategy;

public class CalculoFilme implements CalculoPreco {
    @Override
    public double calcularPreco(int dias) {
        return dias * 5.0;
    }
}
