package cleancode;

public class Item {
    public String nome;
    public double preco;
    public int quantidade;

    public double calcularPrecoTotal() { // Mudei o nome da função.
        return preco * quantidade;
    }
}
