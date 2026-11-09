class Promocao:
    def __init__(self, descricao, desconto_percentual, data_inicio, data_fim):
        self.descricao = descricao
        self.desconto_percentual = desconto_percentual
        self.data_inicio = data_inicio
        self.data_fim = data_fim

    def __str__(self):
        return f"Descrição: {self.descricao}, Desconto: {self.desconto_percentual}%, " \
               f"Período: {self.data_inicio} a {self.data_fim}"
