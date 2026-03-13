package sobrecraga;

import java.util.Scanner;

public class TesteSobrecarga {
    private static void main(final String[] args) {
        Calculadora calc = new Calculadora();
        Scanner teclado = new Scanner(System.in);

        int numInt1 = teclado.nextInt();
        int numInt2 = teclado.nextInt();

        System.out.println(calc.calculo(numInt1, numInt2));

        int numInt3 = teclado.nextInt();
        int numInt4 = teclado.nextInt();
        int numInt5 = teclado.nextInt();

        System.out.println(calc.calculo(numInt3, numInt4, numInt5));

        double numDouble1 = teclado.nextDouble();
        double numDouble2 = teclado.nextDouble();

        System.out.println(calc.calculo(numDouble1, numDouble2));
    }
}
