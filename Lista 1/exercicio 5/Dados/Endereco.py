class Endereco:

    def __init__(self):
        self.cep = ""
        self.complemento = ""
        self.pais = ""

    def get_cep(self):
        return self.cep

    def set_cep(self, cep):
        self.cep = cep

    def get_complemento(self):
        return self.complemento

    def set_complemento(self, complemento):
        self.complemento = complemento

    def get_pais(self):
        return self.pais

    def set_pais(self, pais):
        self.pais = pais

    def __init__(self, cep, complemento):
        self.cep = cep
        self.complemento = complemento
