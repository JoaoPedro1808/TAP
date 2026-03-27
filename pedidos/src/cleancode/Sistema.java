package cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Sistema {

    Scanner sc = new Scanner(System.in);
    List<Pedido> pedidos = new ArrayList<>();
    Db db = new Db();

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
        int tipoDoCliente = 0;
        try {
            tipoDoCliente = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("tipo errado, vai comum");
            tipoDoCliente = 1;
        }

        Cliente cliente = new Cliente();
        cliente.id = pedidos.size() + 1;
        cliente.nome = nomeCliente;
        cliente.tipo = tipoDoCliente;
        cliente.email = nomeCliente.replace(" ", "").toLowerCase() + "@email.com";

        Pedido pedido = new Pedido();
        pedido.id = pedidos.size() + 1;
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

        double total = 0;
        for (int i = 0; i < pedido.itens.size(); i++) {
            total = total + (pedido.itens.get(i).preco * pedido.itens.get(i).quantidade);
        }

        double subtotal = pedido.precoTotal();
        double totalComDesconto = aplicarDesconto(subtotal, cliente.tipo);
        pedido.total = totalComDesconto + calcularFrete(totalComDesconto);

        pedidos.add(pedido);
        db.salvarNoBanco(pedido);

        System.out.println("Pedido criado com sucesso");
        System.out.println("Id: " + pedido.id);
        System.out.println("Cliente: " + pedido.cliente.nome);
        System.out.println("Total: " + pedido.total);

        if (pedido.total > 500) {
            System.out.println("Pedido importante!!!");
        }
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("sem pedidos");
        } else {
            for (Pedido pedido : pedidos) {
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

        Pedido pedidoEncontrado = db.obterId(id);

        if (pedidoEncontrado != null) {
            System.out.println("Pedido encontrado");
            System.out.println("id: " + pedidoEncontrado.id);
            System.out.println("cliente: " + pedidoEncontrado.cliente.nome);
            System.out.println("status: " + pedidoEncontrado.status);
            System.out.println("total: " + pedidoEncontrado.total);

            System.out.println("subtotal (itens): " + pedidoEncontrado.precoTotal()); // Para colocra o total aqui
            System.out.println("Categoria do cliente: " + pedidoEncontrado.cliente.tipoCliente());

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
        Db bancoDadosSistema = new Db();
        Relatorio relatorio = new Relatorio(bancoDadosSistema);
        relatorio.relatorioCompleto();
    }

    public void cancelarPedido() {
        System.out.println("Digite id do pedido");
        int id = Integer.parseInt(sc.nextLine());
        Pedido idPedido = db.obterId(id);

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

    public double aplicarDesconto(double valor, int tipoCliente) {
        if (tipoCliente == 1 && valor > 300) {
            return valor - (valor * 0.05);
        } else if (tipoCliente == 2) {
            return valor > 200 ? valor - (valor * 0.10) : valor - (valor * 0.03);
        } else if (tipoCliente == 3) {
            return valor - (valor * 0.15);
        }
        return valor;
    }

    public double calcularFrete(double valor) {
        if (valor < 100) {
            return 25.0;
        } else if (valor >= 100 && valor < 300) {
            return 15.0;
        }
        return 0.0;
    }
}
