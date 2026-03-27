package cleancode;

import java.util.List;

public interface InfoBanco {
    Pedido obterId(int id);
    List<Pedido> obterBanco();
}
