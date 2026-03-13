package sobrescrita;

public class TesteSobrescrita {
    public static void main(String[] args) {
        Animal gato = new Gato();
        Animal cachorro = new Cachorro();
        Animal animal = new Animal();

        gato.emitirsom();
        cachorro.emitirsom();
        animal.emitirsom();
    }
}
