from Dados.Caixa import Caixa
from Dados.Carrinho import Carrinho
from Dados.Cliente import Cliente
from Dados.Endereco import Endereco
from Dados.Funcionario import Funcionario
from Dados.Produto import Produto
from Dados.Promocao import Promocao

class Supermercado:

    def __init__(self):
        self.caixa = Caixa()
        self.listaClientes = []
        self.listaProdutos = []

    def consultar_caixa(self):
        print("\n* Consulta de Caixa *")
        print("Método de pagamento:", self.caixa.getPagamento())
        print("Total de vendas:", self.caixa.getTotalVendas())
        print("Total de dinheiro:", self.caixa.getTotalDinheiro())

    def consultar_carrinho(self):
        print("\n* Consulta de Carrinho *")
        nome_cliente = input("Digite o nome do cliente para consultar o carrinho: ")
        cliente_consultado_carrinho = None
        for c in self.listaClientes:
            if c.getNome().lower() == nome_cliente.lower():
                cliente_consultado_carrinho = c
                break

        if cliente_consultado_carrinho:
            carrinho_cliente = cliente_consultado_carrinho.getCarrinho()
            print("\nCarrinho do cliente", cliente_consultado_carrinho.getNome() + ":")

            print("Produtos no carrinho:")
            for p in carrinho_cliente.getProdutos():
                print("Nome:", p.getNome(), "| Preço:", p.getPreco())
            print("Quantidade total:", carrinho_cliente.getQuantidade())
            print("Valor total:", carrinho_cliente.getValortotal())
        else:
            print("Cliente não encontrado.")

    def consultar_clientes(self):
        print("\n* Lista de Clientes *")
        for c in self.listaClientes:
            print("Nome:", c.getNome(), "| Email:", c.getEmail())

        nome_cliente_consulta = input("Digite o nome do cliente para ver detalhes: ")
        cliente_consultado_detalhes = None
        for c in self.listaClientes:
            if c.getNome().lower() == nome_cliente_consulta.lower():
                cliente_consultado_detalhes = c
                break

        if cliente_consultado_detalhes:
            print("\nDetalhes do cliente", cliente_consultado_detalhes.getNome() + ":")
            print("Email:", cliente_consultado_detalhes.getEmail())
        else:
            print("Cliente não encontrado.")

    def consultar_endereco(self):
        print("\n* Lista de Clientes *")
        for c in self.listaClientes:
            print("Nome:", c.getNome(), "| Email:", c.getEmail())

        nome_cliente_consulta_endereco = input("Digite o nome do cliente para ver o endereço: ")
        cliente_consultado_endereco = None
        for c in self.listaClientes:
            if c.getNome().lower() == nome_cliente_consulta_endereco.lower():
                cliente_consultado_endereco = c
                break

        if cliente_consultado_endereco:
            endereco_cliente = cliente_consultado_endereco.getEndereco()
            print("\nDetalhes do endereço:")
            print("CEP:", endereco_cliente.getCep())
            print("Complemento:", endereco_cliente.getComplemento())
            print("País:", endereco_cliente.getPais())
        else:
            print("Cliente não encontrado.")

    def consultar_produtos(self):
        print("\n* Consulta de Produtos *")
        print("Produtos disponíveis no supermercado:")
        for p in self.listaProdutos:
            print("Nome:", p.getNome(), "| Preço:", p.getPreco(), "| Validade:", p.getValidade())

    def consultar_funcionarios(self):
        print("\n* Lista de Funcionários *")
        for funcionario in self.listaFuncionarios:
            print("Nome:", funcionario.getNome(), "| Cargo:", funcionario.getCargo())

    def consultar_promocoes(self):
        print("\n* Lista de Promoções *")
        for promocao in self.listaPromocoes:
            print("Descrição:", promocao.getDescricao(), "| Desconto:", promocao.getDesconto())

supermercado = Supermercado()

while True:
    print("\n* Supermercado Kokhai *")
    print("1. Consultar o caixa")
    print("2. Consultar carrinho")
    print("3. Consultar clientes")
    print("4. Consultar endereço")
    print("5. Consultar produtos")
    print("6. Consultar funcionários")
    print("7. Consultar promoções")

    opcao = int(input())

    if opcao == 1:
        supermercado.consultar_caixa()
    elif opcao == 2:
        supermercado.consultar_carrinho()
    elif opcao == 3:
        supermercado.consultar_clientes()
    elif opcao == 4:
        supermercado.consultar_endereco()
    elif opcao == 5:
        supermercado.consultar_produtos()
    elif opcao == 6:
        supermercado.consultar_funcionarios()
    elif opcao == 7: 
        supermercado.consultar_promocoes()
    else:
        print("Opção inválida. Por favor, escolha uma opção válida.")
