import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        float[] x = new float[5];
        String[] p = new String[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("\nDigite seu nome: ");
            p[i] = scan.nextLine();

            System.out.print("Digite sua idade: ");
            x[i] = scan.nextFloat();

            scan.nextLine();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (x[i] < x[j]) {
                    float temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;
                    String tep = p[i];
                    p[i] = p[j];
                    p[j] = tep;
                }

            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.print("\nNome: " + p[i] + " Idade: " + x[i]);
        }
    }
}