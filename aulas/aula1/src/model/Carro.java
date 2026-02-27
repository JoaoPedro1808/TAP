package model;

public class Carro implements IVeiculo{
    String placa;

    @Override
    public String acelerar() {
        return "Acelerando o carro";
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
