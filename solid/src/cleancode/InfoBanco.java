package cleancode;

import java.util.ArrayList;
import java.util.List;

public class InfoBanco implements Leiturabanco{
    public List<Pedido> banco;

    public InfoBanco(List<Pedido> banco) {
        this.banco = banco;
    }

    @Override
    public List<Pedido> obterBanco() {
        return banco;
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
}
