package pack2;
import pack1.A;
import pack1.B;

public class C {
    private int at5;
    public int at6;
    protected void mt5(){
        A a = new A();
        // a.
        // So ve o public de A.
        B b = new B();
        // b.
        // So ve o public de A e B.
        D d = new D();
        // d.
        // Ve o public de A, B e C e o protected e package de D.
    }
    void mt6() {

    }
}
