// Classe propia para o cliente premium herdando do cliente sistema

public class ClientePremium extends ClienteSistema{
    public ClientePremium() {
        this.tipo = 2;
    }

    @Override
    public String obterTipoCliente() {
        return "";
    }
}
