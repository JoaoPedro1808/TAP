package cleancode;

import java.util.List;

public class SalvarBanco implements ArmazenarBanco{
    public List<Pedido> banco;

    public SalvarBanco(List<Pedido> banco) {
        this.banco = banco;
    }

    @Override
    public void salvarNoBanco(Pedido pedido) {
        try {
            banco.add(pedido);
            System.out.println("salvou no banco");
        } catch (Exception e) {
            System.out.println("erro ao salvar");
        }
    }
}
