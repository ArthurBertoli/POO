package dados;

public class Aquario extends Viveiro {
    private float altura;
    private float temperatura;

    public Aquario(int maxPeixes) {
        super(maxPeixes);
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float calculaEspaco() {
        return super.calculaEspaco() * altura;
    }

    public boolean adicionarAnimal(Animal animal) {
        if (espacoDisponivel() >= (animal.calculaEspacoOcupado() * 0.7)) {
            if (animal instanceof Peixe) {
                Peixe p = (Peixe) animal;
                if ((p.getTemperaturaIdeal() < temperatura - 3 || p.getTemperaturaIdeal() > temperatura + 3)) {
                    return false;
                }
            }
            animais[quantAnimais] = animal;
            quantAnimais++;
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append("\nAquário ");
        string.append("\nNome: " + nome + "\n");
        for (int i = 0; i < quantAnimais; i++) {
            string.append("\t" + animais[i].toString() + "\n");
        }
        return string.toString();
    }
}