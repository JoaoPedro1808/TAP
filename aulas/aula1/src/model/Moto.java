package model;

public class Moto implements IVeiculo {
    String placa;

    @Override
    public String acelerar() {
        return "Acelerando a moto";
    }

    @Override
    public String getPlaca() {
        return this.placa;
    }

    @Override
    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
