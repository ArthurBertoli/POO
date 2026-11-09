class Caixa:

    def __init__(self):
        self.pagamento = ""
        self.totalVendas = 0.0
        self.totalDinheiro = 0.0

    def get_pagamento(self):
        return self.pagamento

    def set_pagamento(self, pagamento):
        self.pagamento = pagamento

    def registrar_venda(self, quantidade):
        self.totalVendas += quantidade

    def get_total_vendas(self):
        return self.totalVendas

    def registrar_dinheiro(self, valor):
        self.totalDinheiro += valor

    def get_total_dinheiro(self):
        return self.totalDinheiro

    def __init__(self, pagamento, totalVendas, totalDinheiro):
        self.pagamento = pagamento
        self.totalVendas = totalVendas
        self.totalDinheiro = totalDinheiro
