package apresentacao;

import dados.Crocodilo;
import dados.Coruja;
import dados.Beluga;

public class Main {
    public static void main(String[] args) {
        Crocodilo crocodilo1 = new Crocodilo("Filipino");
        Crocodilo crocodilo2 = new Crocodilo("Orinoco");

        Coruja coruja1 = new Coruja("Orelhuda");
        Coruja coruja2 = new Coruja("Caburé");

        Beluga beluga1 = new Beluga("Leukos");
        Beluga beluga2 = new Beluga("Delphina");

        System.out.println(crocodilo1.getNome() + ": " + crocodilo1.emitirSom());
        System.out.println(crocodilo2.getNome() + ": " + crocodilo2.emitirSom());

        System.out.println(coruja1.getNome() + ": " + coruja1.emitirSom());
        System.out.println(coruja2.getNome() + ": " + coruja2.emitirSom());

        System.out.println(beluga1.getNome() + ": " + beluga1.emitirSom());
        System.out.println(beluga2.getNome() + ": " + beluga2.emitirSom());
    }
}
