
import java.util.List;
import java.util.ArrayList;

public class Equipe {
    private String nome;
    private List<Aluno> membros = new ArrayList<>();

    public Equipe(String nome) {
        this.nome = nome;
    }

    public void adicionarMembro(Aluno aluno) {
        membros.add(aluno);
    }

    public List<Aluno> getMembros() {
        return membros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
