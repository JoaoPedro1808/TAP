package cleancode;

import java.util.ArrayList;
import java.util.List;

public class Db {

    public static List<Pedido> banco = new ArrayList<>();

    public void save(Pedido pedido) { // Mudei o "p" para "pedido"
        try {
            banco.add(pedido);
            System.out.println("salvou no banco");
        } catch (Exception e) {
            System.out.println("erro ao salvar");
        }
    }

    public List<Pedido> obterBanco() { // Mudei o nome da função
        return banco;
    }

    public Pedido obterId(int id) { // Mudei o nome da função e encurtei o "for"
        for (Pedido pedido : banco) {
            if (pedido.id == id) {
                return pedido;
            }
        }
        return null;
    }
}
