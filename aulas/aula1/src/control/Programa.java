package control;

import model.Carro;
import model.Moto;

public class Programa {
    public static void main(String[] args) {
        Carro carro = new Carro();
        Moto moto = new Moto();

        carro.setPlaca("QRD0J15");
        moto.setPlaca("HYG1N43");

        System.out.println("Moto: " + moto.acelerar() + " | " + "Placa: " + moto.getPlaca());
        System.out.println("Carro: " + carro.acelerar() + " | " + "Placa: " + carro.getPlaca());
    }
}
