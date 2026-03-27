package cleancode;

import java.util.List;

public class Relatorio {
    int totalPedidos = 0;
    double valorTotal = 0;
    int cancelados = 0;
    int clientesComuns = 0;
    int clientesPremiums = 0;
    int clientesVips = 0;

    public InfoBanco infobanco;

    public Relatorio (InfoBanco infobanco) {
        this.infobanco = infobanco;
    }

    public void relatorioCompleto() {
        System.out.println("======= RELATÓRIO =======");

        List<Pedido> pedidos = this.infobanco.obterBanco();
        this.totalPedidos = pedidos.size();

        for (Pedido pedido : pedidos) {
            this.valorTotal += pedido.total;

            if ("CANCELADO".equals(pedido.status)) {
                this.cancelados++;
            }

            switch (pedido.cliente.tipo) {
                case 1 -> this.clientesComuns++;
                case 2 -> this.clientesPremiums++;
                case 3 -> this.clientesVips++;
            }
            imprimirDetalhesPedido(pedido);
        }
        imprimirResumo();
        avaliarDesempenho();
    }

    public void imprimirDetalhesPedido(Pedido pedido) {
        System.out.println("Pedido " + pedido.id + " - " + pedido.cliente.nome + " - " + pedido.total + " - " + pedido.status);

        for (int j = 0; j < pedido.itens.size(); j++) {
            Item item = pedido.itens.get(j);
            System.out.println("   item: " + item.nome + " qtd:" +item.quantidade + " preco:" + item.preco);
        }
    }

    public void imprimirResumo() {
        System.out.println("--------------------");
        System.out.println("qtd pedidos: " + this.totalPedidos);
        System.out.println("valor total: " + this.valorTotal);
        System.out.println("cancelados: " + this.cancelados);
        System.out.println("clientes comuns: " + this.clientesComuns);
        System.out.println("clientes premium: " + this.clientesPremiums);
        System.out.println("clientes vip: " + this.clientesVips);

        double mediaPreco = this.totalPedidos > 0 ? (this.valorTotal / this.totalPedidos) : 0;
        System.out.println("media: " + mediaPreco);
    }

    public void avaliarDesempenho() {
        if (this.valorTotal > 1000) {
            System.out.println("resultado muito bom");
        } else if (this.valorTotal > 500) {
            System.out.println("resultado ok");
        } else {
            System.out.println("resultado fraco");
        }
    }
}