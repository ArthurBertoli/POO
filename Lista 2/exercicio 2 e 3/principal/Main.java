import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite os elementos da matriz 5x5 (digite -1 para parar):");
        Matriz<Integer> matriz = new Matriz<>(5, 5);

        int menorQuadrante = Integer.MAX_VALUE;
        int elemento = -1;
        while (true) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    elemento = scanner.nextInt();
                    if (elemento == -1) {
                        break;
                    }
                    matriz.set(elemento, i, j);

                    if (i < 3 && j < 3) {
                        menorQuadrante = Math.min(menorQuadrante, elemento);
                    }
                }
                if (elemento == -1) {

                    break;
                }
            }
            if (elemento == -1) {
                break;
            }
        }
        System.out.println("Menor elemento dos quadrantes: " + menorQuadrante);

        scanner.close();
    }
}
