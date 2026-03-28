import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Sistema {
    Scanner sc = new Scanner(System.in);
    List<Pedido> banco = new ArrayList<>();
    SalvarBanco salvarBanco = new SalvarBanco(banco);
    LerDados lerDados = new LerDados(banco);
    CalculadoraPedido calculadoraPedido = new CalculadoraPedido();

    public void run() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("==== SISTEMA ====");
            System.out.println("1 - Novo pedido");
            System.out.println("2 - Listar pedidos");
            System.out.println("3 - Buscar pedido por id");
            System.out.println("4 - Relatorio");
            System.out.println("5 - Cancelar pedido");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("erro");
                continue;
            }

            switch (opcao) {
                case 1 -> solicitarNovoPedido();
                case 2 -> listarPedidos();
                case 3 -> buscarPedidosID();
                case 4 -> gerarRelatorio();
                case 5 -> cancelarPedido();
                case 0 -> System.out.println("fim");
                default -> System.out.println("opcao invalida");
            }
        }
    }

    public void solicitarNovoPedido() {
        System.out.println("Nome do cliente:");
        String nomeCliente = sc.nextLine();

        System.out.println("Tipo cliente (1 comum, 2 premium, 3 vip):");
        ClienteSistema cliente;
        int tipoDoCliente = 0;
        try {
            tipoDoCliente = Integer.parseInt(sc.nextLine());
            switch (tipoDoCliente) {
                case 2 -> cliente = new ClientePremium();
                case 3 -> cliente = new ClienteVip();
                default -> cliente = new ClienteComun();
            }
        } catch (Exception e) {
            System.out.println("tipo errado, vai comum");
            cliente = new ClienteComun();
        }

        cliente.id = banco.size() + 1;
        cliente.nome = nomeCliente;
        cliente.email = nomeCliente.replace(" ", "").toLowerCase() + "@email.com";

        Pedido pedido = new Pedido();
        pedido.id = banco.size() + 1;
        pedido.cliente = cliente;
        pedido.status = "NOVO";
        pedido.itens = new ArrayList<>();

        String continua = "s";
        while (continua.equalsIgnoreCase("s")) {
            System.out.println("Nome do item:");
            String nomeItem = sc.nextLine();

            System.out.println("Preço do item:");
            double precoItem = 0;
            try {
                precoItem = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Preço invalido");
                precoItem = 0;
            }

            System.out.println("Qtd:");
            int quantidadePedido = 0;
            try {
                quantidadePedido = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Quantidade invalida");
                quantidadePedido = 1;
            }

            Item item = new Item();
            item.nome = nomeItem;
            item.preco = precoItem;
            item.quantidade = quantidadePedido;
            pedido.itens.add(item);

            System.out.println("Adicionar mais item? s/n");
            continua = sc.nextLine();
        }

        double subtotal = pedido.precoTotal();
        double totalComDesconto = calculadoraPedido.aplicarDesconto(subtotal, cliente.tipo);
        pedido.total = totalComDesconto + calculadoraPedido.calcularFrete(totalComDesconto);

        salvarBanco.salvarNoBanco(pedido);

        System.out.println("Pedido criado com sucesso");
        System.out.println("Id: " + pedido.id);
        System.out.println("Cliente: " + pedido.cliente.nome);
        System.out.println("Total: " + pedido.total);

        if (pedido.total > 500) {
            System.out.println("Pedido importante!!!");
        }
    }

    public void listarPedidos() {
        if (banco.isEmpty()) {
            System.out.println("sem pedidos");
        } else {
            for (Pedido pedido : banco) {
                System.out.println("---------------");
                pedido.detalhesPedido();
                for (int j = 0; j < pedido.itens.size(); j++) {
                    Item it = pedido.itens.get(j);
                    System.out.println(it.nome + " - " + it.quantidade + " - " + it.preco);
                }
            }
        }
    }

    public void buscarPedidosID() {
        System.out.println("Digite o id:");
        int id = Integer.parseInt(sc.nextLine());

        Pedido pedidoEncontrado = lerDados.obterId(id);

        if (pedidoEncontrado != null) {
            System.out.println("Pedido encontrado");
            System.out.println("id: " + pedidoEncontrado.id);
            System.out.println("cliente: " + pedidoEncontrado.cliente.nome);
            System.out.println("status: " + pedidoEncontrado.status);
            System.out.println("total: " + pedidoEncontrado.total);

            System.out.println("subtotal (itens): " + pedidoEncontrado.precoTotal());
            System.out.println("Categoria do cliente: " + pedidoEncontrado.cliente.obterTipoCliente());

            for (int j = 0; j < pedidoEncontrado.itens.size(); j++) {
                Item item = pedidoEncontrado.itens.get(j);
                System.out.println("item " + (j + 1) + ": " + item.nome + " / " + item.quantidade + " / " + item.preco);
            }
        }
        else {
            System.out.println("Pedido não encontrado");
        }
    }

    public void gerarRelatorio() {
        Relatorio relatorio = new Relatorio(lerDados);
        relatorio.relatorioCompleto();
    }

    public void cancelarPedido() {
        System.out.println("Digite id do pedido");
        int id = Integer.parseInt(sc.nextLine());
        Pedido idPedido = lerDados.obterId(id);

        if (idPedido == null) {
            System.out.println("pedido não existe");
            return;
        }

        if (idPedido.status.equals("Cancelado")) {
            System.out.println("Pedido ja cancelado");
            return;
        }

        System.out.println("Deseja relamente cancelar o pedido" + idPedido.id + "(s/n)");
        String confirmacao = sc.nextLine();

        if (Objects.equals(confirmacao, "s")) {
            idPedido.status = "Cancelado";
            System.out.println("Cancelado");
        } else {
            System.out.println("Operação abortada");
        }
    }
}
