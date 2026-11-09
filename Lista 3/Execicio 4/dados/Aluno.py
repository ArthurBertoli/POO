from pessoa import Pessoa

class Aluno(Pessoa):
    def __init__(self, nome, notas):
        super().__init__(nome)
        self.notas = notas
    
    def calcularMedia(self):
        if len(self.notas) > 0:
            media = sum(self.notas) / len(self.notas)
            return media
        else:
            return 0
    
    def __repr__(self):
        media = self.calcularMedia()
        status = "Aprovado" if media >=7 else "Em exame"
        return f"Aluno(nome='{self.nome}', notas={self.notas}, média={media}, status={status})"