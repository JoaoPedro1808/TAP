// Classe propia para o cliente vip herdando do cliente sistema

public class ClienteVip extends ClienteSistema{
    public ClienteVip() {
        this.tipo = 3;
    }

    @Override
    public String obterTipoCliente() {
        return "";
    }
}
