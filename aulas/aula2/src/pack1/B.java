package pack1;
import pack2.C;
import pack2.D;

public class B extends A{
    private int at3;
    public int at4;
    protected void mt3(){
        A a = new A();
        // a.
        // Ve o public, procteted e package de A.
        C c = new C();
        // c.
        // So ve o public de C.
        D d = new D();
        // d.
        // Ve o public de A, B e C e o protected de A e B
    }
    void mt4() {

    }
}
