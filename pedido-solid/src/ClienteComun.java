public class ClienteComun extends ClienteSistema {
    public ClienteComun() {
        this.tipo = 1;
    }

    @Override
    public String obterTipoCliente() {
        return "Comun";
    }

}
