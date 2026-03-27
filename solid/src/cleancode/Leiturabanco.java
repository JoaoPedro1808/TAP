package cleancode;

import java.util.List;

public interface Leiturabanco {
    List<Pedido> obterBanco();
    Pedido obterId(int id);
}
