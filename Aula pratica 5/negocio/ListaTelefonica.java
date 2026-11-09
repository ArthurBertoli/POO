package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Contato;

public class ListaTelefonica {

    private List<Contato> contatos;

    public ListaTelefonica() {
        this.contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) {
        contatos.add(contato);
    }

    public void removerContato(Contato contato) {
        if (contatos.contains(contato)) {
            contatos.remove(contato);
        }
    }

    public List<Contato> buscarContatos(String inicial) {
        List<Contato> contatosEncontrados = new ArrayList<>();

        for (Contato contato : contatos) {
            if (contato.getNome().toLowerCase().startsWith(inicial.toLowerCase())) {
                contatosEncontrados.add(contato);
            }
        }
        return contatosEncontrados;
    }

}