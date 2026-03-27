package cleancode;

public class Cliente {
    public int id;
    public String nome;
    public String email;
    public int tipo;

    public String tipoCliente() {
        return switch (tipo) {
            case 1 -> "Comum";
            case 2 -> "Premium";
            case 3 -> "Vip";
            default -> "Outro";
        };
    }
}