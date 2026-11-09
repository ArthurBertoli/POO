import json

class Filme:
    def __init__(self, titulo, ano, classificacao, nota):
        self.titulo = titulo
        self.ano = ano
        self.classificacao = classificacao
        self.nota = nota

    def __str__(self):
        return f"{self.titulo} ({self.ano}) - Classificação: {self.classificacao} - Nota: {self.nota}"

def ler_json_arquivo(nome_arquivo):
    filmes = []
    with open(nome_arquivo, 'r') as arquivo:
        dados = json.load(arquivo)
        for filme_json in dados:
            filme = Filme(filme_json['titulo'], filme_json['ano'], filme_json['classificacao'], filme_json['nota'])
            filmes.append(filme)
    return filmes

def listar_filmes_por_nota(filmes):
    filmes_ordenados = sorted(filmes, key=lambda x: x.nota, reverse=True)
    for filme in filmes_ordenados:
        print(filme)

nome_arquivo = 'filmes.json'

filmes = ler_json_arquivo(nome_arquivo)

listar_filmes_por_nota(filmes)
