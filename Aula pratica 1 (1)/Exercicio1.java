import java.util.Scanner;

public class Exercicio1 {

    public static void main(String[] args) {

        float[] x = new float[5];
        Scanner scan = new Scanner(System.in);
        float media;
        float valor = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print("\nDigite um valor: ");
            x[i] = scan.nextFloat();
            valor = valor + x[i];
        }

        media = valor / 5;
        System.out.print("\nMédia: " + media);
    }
}