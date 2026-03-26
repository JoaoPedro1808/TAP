package cleancode;
import java.util.List;

public class Pedido {
    public int id;
    public Cliente cliente;
    public List<Item> itens;
    public double total;
    public String status;

    public double precoTotal() { // Mudei o nome da função.
        double subTotal = 0;
        for (Item item : itens) {
            subTotal += item.calcularPrecoTotal();
        }
        return subTotal;
    }

    public void detalhesPedido() { // Mudei o nome da função.
        System.out.println("Pedido " + id);
        System.out.println("Cliente " + cliente.nome);
        for (Item iten : itens) { // Diminui o "for".
            System.out.println(iten.nome);
        }
        System.out.println(total);
    }
}
