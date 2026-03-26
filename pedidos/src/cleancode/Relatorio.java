package cleancode;

import java.util.List;

public class Relatorio {
    //Transformei todas as variaveis em atributos da classe
    int totalPedidos = 0; // Pegar o tamnho da lista
    double valorTotal = 0; // Para armazenar o valor toatal
    int cancelados = 0;
    int clientesComuns = 0; // Mudei o nome da variavel
    int clientesPremiums = 0; // Mudei o nome da variavel
    int clientesVips = 0; // Mudei o nome da variavel

    public void relatorioCompleto(List<Pedido> pedidos) { // Encurtei a função e tirei algumas outra coisas para colocar em outras funções
        System.out.println("======= RELATÓRIO =======");
        this.totalPedidos = pedidos.size();

        for (Pedido pedido : pedidos) { // Encurtei o "for"
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
        imprimirResumo(); // Chamada da função
        avaliarDesempenho(); // Chamada da função
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
        // Coloquei as chamadas dos atributos em vez dos paramentros
        System.out.println("qtd pedidos: " + this.totalPedidos);
        System.out.println("valor total: " + this.valorTotal);
        System.out.println("cancelados: " + this.cancelados);
        System.out.println("clientes comuns: " + this.clientesComuns);
        System.out.println("clientes premium: " + this.clientesPremiums);
        System.out.println("clientes vip: " + this.clientesVips);

        double mediaPreco = this.totalPedidos > 0 ? (this.valorTotal / this.totalPedidos) : 0;
        System.out.println("media: " + mediaPreco);
    }

    public void avaliarDesempenho() { // Função para imprimir o desempenho
        if (this.valorTotal > 1000) {
            System.out.println("resultado muito bom");
        } else if (this.valorTotal > 500) {
            System.out.println("resultado ok");
        } else {
            System.out.println("resultado fraco");
        }
    }
}