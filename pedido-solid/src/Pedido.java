import java.util.List;

public class Pedido {
    public int id;
    public ClienteSistema cliente;
    public List<Item> itens;
    public double total;
    public String status;

    public double precoTotal() {
        double subTotal = 0;
        for (Item item : itens) {
            subTotal += item.calcularPrecoTotal();
        }
        return subTotal;
    }

    public void detalhesPedido() {
        System.out.println("Pedido " + id);
        System.out.println("Cliente " + cliente.nome);

        for (Item iten : itens) {
            System.out.println(iten.nome);
        }
        System.out.println(total);
    }
}
