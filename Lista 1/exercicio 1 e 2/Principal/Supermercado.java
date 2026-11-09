package Principal;

import Dados.Caixa;
import Dados.Carrinho;
import Dados.Cliente;
import Dados.Endereco;
import Dados.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supermercado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Caixa caixa = new Caixa();
        Carrinho carrinho = new Carrinho();
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        Produto produto = new Produto();
        List<Cliente> listaClientes = new ArrayList<>();
        List<Produto> listaProdutos = new ArrayList<>();

        while (true) {
            System.out.println("\n* Supermercado Kokhai *");
            System.out.println("\n1. Consultar o caixa");
            System.out.println("\n2. Consultar carrinho");
            System.out.println("\n3. Consultar clientes");
            System.out.println("\n4. Consultar endereco");
            System.out.println("\n5. Consultar produtos");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n* Consulta de Caixa *");
                    System.out.println("Método de pagamento: " + caixa.getPagamento());
                    System.out.println("Total de vendas: " + caixa.getTotalVendas());
                    System.out.println("Total de dinheiro: " + caixa.getTotalDinheiro());
                    break;

                case 2:
                    System.out.println("\n* Consulta de Carrinho *");
                    System.out.println("Digite o nome do cliente para consultar o carrinho:");
                    String nomeCliente = scanner.next();

                    Cliente clienteConsultadoCarrinho = null;
                    for (Cliente c : listaClientes) {
                        if (c.getNome().equalsIgnoreCase(nomeCliente)) {
                            clienteConsultadoCarrinho = c;
                            break;
                        }
                    }

                    if (clienteConsultadoCarrinho != null) {
                        Carrinho carrinhoCliente = clienteConsultadoCarrinho.getCarrinho();
                        System.out.println("\nCarrinho do cliente " + clienteConsultadoCarrinho.getNome() + ":");

                        System.out.println("Produtos no carrinho:");
                        for (Produto p : carrinhoCliente.getProdutos()) {
                            System.out.println("Nome: " + p.getNome() + " | Preço: " + p.getPreco());
                        }
                        System.out.println("Quantidade total: " + carrinhoCliente.getQuantidade());
                        System.out.println("Valor total: " + carrinhoCliente.getValortotal());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }

                    break;

                case 3:
                    System.out.println("\n* Lista de Clientes *");
                    for (Cliente c : listaClientes) {
                        System.out.println("Nome: " + c.getNome() + " | Email: " + c.getEmail());
                    }

                    System.out.println("Digite o nome do cliente para ver detalhes:");
                    String nomeClienteConsulta = scanner.next();

                    Cliente clienteConsultadoDetalhes = null;
                    for (Cliente c : listaClientes) {
                        if (c.getNome().equalsIgnoreCase(nomeClienteConsulta)) {
                            clienteConsultadoDetalhes = c;
                            break;
                        }
                    }

                    if (clienteConsultadoDetalhes != null) {
                        System.out.println("\nDetalhes do cliente " + clienteConsultadoDetalhes.getNome() + ":");
                        System.out.println("Email: " + clienteConsultadoDetalhes.getEmail());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }

                    break;

                case 4:
                    System.out.println("\n* Lista de Clientes *");
                    for (Cliente c : listaClientes) {
                        System.out.println("Nome: " + c.getNome() + " | Email: " + c.getEmail());
                    }

                    System.out.println("Digite o nome do cliente para ver o endereço:");
                    String nomeClienteConsultaEndereco = scanner.next();

                    Cliente clienteConsultadoEndereco = null;
                    for (Cliente c : listaClientes) {
                        if (c.getNome().equalsIgnoreCase(nomeClienteConsultaEndereco)) {
                            clienteConsultadoEndereco = c;
                            break;
                        }
                    }

                    if (clienteConsultadoEndereco != null) {
                        Endereco enderecoCliente = clienteConsultadoEndereco.getEndereco();
                        System.out.println("\nDetalhes do endereço:");
                        System.out.println("CEP: " + enderecoCliente.getCep());
                        System.out.println("Complemento: " + enderecoCliente.getComplemento());
                        System.out.println("País: " + enderecoCliente.getPais());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }

                    break;

                case 5:
                    System.out.println("\n* Consulta de Produtos *");
                    System.out.println("Produtos disponíveis no supermercado:");
                    for (Produto p : listaProdutos) {
                        System.out.println("Nome: " + p.getNome() + " | Preço: " + p.getPreco() + " | Validade: "
                                + p.getValidade());
                    }
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }
}