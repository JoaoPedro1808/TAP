// Interface para obter as informações do banco de dados, funcionando na classe LerDados.

import java.util.List;

public interface InfoPedido {
    Pedido obterId(int id);
    List<Pedido> obterBanco();
}
