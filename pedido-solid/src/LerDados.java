import java.util.List;

public class LerDados implements InfoPedido {
    public List<Pedido> banco;

    public LerDados(List<Pedido> banco) {
        this.banco = banco;
    }

    @Override
    public Pedido obterId(int id) {
        for (Pedido pedido : banco) {
            if (pedido.id == id) {
                return pedido;
            }
        }
        return null;
    }

    @Override
    public List<Pedido> obterBanco() {
        return banco;
    }
}
