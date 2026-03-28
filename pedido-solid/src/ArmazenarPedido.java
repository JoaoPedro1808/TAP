// Interface para salvar os pedidos no banco de dados, usado na classe de SalvarBanco.

public interface ArmazenarPedido {
    void salvarNoBanco(Pedido pedido);
}
