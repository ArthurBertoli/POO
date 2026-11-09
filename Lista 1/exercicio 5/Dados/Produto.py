class Produto:

    def __init__(self):
        self.nome = ""
        self.preco = 0.0
        self.validade = ""

    def get_nome(self):
        return self.nome

    def set_nome(self, nome):
        self.nome = nome

    def get_preco(self):
        return self.preco

    def set_preco(self, preco):
        self.preco = preco

    def get_validade(self):
        return self.validade

    def set_validade(self, validade):
        self.validade = validade

    def __init__(self, nome, preco, validade):
        self.nome = nome
        self.preco = preco
        self.validade = validade
