public class Item {
    public String nome;
    public double preco;
    public int quantidade;

    public double calcularPrecoTotal() {
        return preco * quantidade;
    }
}
