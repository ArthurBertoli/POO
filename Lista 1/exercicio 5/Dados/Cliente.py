class Cliente:

    def __init__(self):
        self.nome = ""
        self.endereco = None
        self.email = ""
        self.carrinho = None

    def get_nome(self):
        return self.nome

    def set_nome(self, nome):
        self.nome = nome

    def get_endereco(self):
        return self.endereco

    def set_endereco(self, endereco):
        self.endereco = endereco

    def get_email(self):
        return self.email

    def set_email(self, email):
        self.email = email

    def get_carrinho(self):
        return self.carrinho

    def set_carrinho(self, carrinho):
        self.carrinho = carrinho

    def __init__(self, nome, email):
        self.nome = nome
        self.email = email
