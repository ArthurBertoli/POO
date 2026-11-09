
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turma {
    private List<Aluno> alunos = new ArrayList<>();

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    private void ordenarAlunosPorMedia() {
        Collections.sort(alunos, (a1, a2) -> Double.compare(a1.calcularMedia(), a2.calcularMedia()));
    }

    public List<Equipe> separarEmEquipes() {
        ordenarAlunosPorMedia();
        List<Equipe> equipes = new ArrayList<>();

        int inicio = 0;
        int fim = alunos.size() - 1;

        while (inicio < fim) {
            Equipe equipe = new Equipe("Equipe " + (equipes.size() + 1));
            equipe.adicionarMembro(alunos.get(inicio));
            equipe.adicionarMembro(alunos.get(fim));
            equipes.add(equipe);
            inicio++;
            fim--;
        }

        return equipes;
    }

}
