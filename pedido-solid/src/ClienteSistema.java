// Classe abstrata para separar os tipos de clientes (ClienteComun, ClientePremium e ClienteVip)

public abstract class ClienteSistema {
    public int id;
    public String nome;
    public String email;
    public int tipo;

    public abstract String obterTipoCliente();
}
