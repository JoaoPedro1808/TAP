package pack2;
import pack1.A;
import pack1.B;

public class D extends B {
    private int at7;
    public int at8;
    protected void mt7(){
        A a = new A();
        // a.
        // So ve o public de A.
        B b = new B();
        // b.
        // Ve o public de A e B.
        C c = new C();
        // c.
        // Ve o public, protected e package de C.
    }
    void mt8() {

    }
}
