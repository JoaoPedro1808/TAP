package cleancode;

public class Cliente {
    public int id;
    public String nome;
    public String email;
    public int tipo; // 1 comum, 2 premium, 3 vip

    public String tipoCliente() { // Mudei o nome da função e troquei o "if" para o "case".
        return switch (tipo) {
            case 1 -> "Comum";
            case 2 -> "Premium";
            case 3 -> "Vip";
            default -> "Outro";
        };
    }
}