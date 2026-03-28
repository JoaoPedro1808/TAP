public abstract class ClienteSistema {
    public int id;
    public String nome;
    public String email;
    public int tipo;

    public abstract String obterTipoCliente();
}
