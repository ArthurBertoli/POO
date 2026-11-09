package apresentacao;

import dados.Circulo;
import dados.Losango;
import dados.TrianguloEquilatero;

public class Main {
    public static void main(String[] args) {

        TrianguloEquilatero triangulo1 = new TrianguloEquilatero(5);
        TrianguloEquilatero triangulo2 = new TrianguloEquilatero(8);

        Losango losango1 = new Losango(10, 6);
        Losango losango2 = new Losango(12, 8);

        Circulo circulo1 = new Circulo(5);
        Circulo circulo2 = new Circulo(8);

        System.out.println("Triângulo 1: " + triangulo1.toString());
        System.out.println("Triângulo 2: " + triangulo2.toString());
        System.out.println("Losango 1: " + losango1.toString());
        System.out.println("Losango 2: " + losango2.toString());
        System.out.println("Círculo 1: " + circulo1.toString());
        System.out.println("Círculo 2: " + circulo2.toString());
    }
}
