package cleancode;

import java.util.List;

public class SalvarBanco implements ArmazenarBanco{
    public List<Pedido> banco;

    public SalvarBanco(List<Pedido> banco) {
        this.banco =banco;
    }

    @Override
    public void salvarNoBanco(Pedido pedido) {
        banco.add(pedido);
    }
}
