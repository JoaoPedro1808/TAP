package pack1;
import pack2.C;
import pack2.D;

public class A {
    private int at1;
    public int at2;
    protected void mt1(){
        B b = new B();
        // b.
        // observa os publics, protected e package do B e A, so não observa os privates.
        C c = new C();
        // c.
        // So observa o public de C.
        D d = new D();
        // d.
        // Ve o public de A, B e C e o procteted de A e B.
    }
    void mt2() {

    }
}
