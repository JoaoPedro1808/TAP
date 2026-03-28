// Classe propia para calcular o desconto e o frete.

public class CalculadoraPedido {
    public double aplicarDesconto(double valor, int tipoCliente) {
        if (tipoCliente == 1 && valor > 300) {
            return valor - (valor * 0.05);
        } else if (tipoCliente == 2) {
            return valor > 200 ? valor - (valor * 0.10) : valor - (valor * 0.03);
        } else if (tipoCliente == 3) {
            return valor - (valor * 0.15);
        }
        return valor;
    }

    public double calcularFrete(double valor) {
        if (valor < 100) {
            return 25.0;
        } else if (valor >= 100 && valor < 300) {
            return 15.0;
        }
        return 0.0;
    }
}
