class Carrinho:

    def __init__(self):
        self.produtos = []
        self.quantidade = 0
        self.valortotal = 0.0

    def get_produtos(self):
        return self.produtos

    def adicionar_produto(self, produto):
        self.produtos.append(produto)
        self.quantidade += 1
        self.valortotal += produto.get_preco()

    def remover_produto(self, produto):
        self.produtos.remove(produto)
        self.quantidade -= 1
        self.valortotal -= produto.get_preco()

    def get_quantidade(self):
        return self.quantidade

    def get_valor_total(self):
        return self.valortotal

    def __init__(self, valortotal, produtos, quantidade):
        self.valortotal = valortotal
        self.quantidade = quantidade
        self.produtos = produtos
