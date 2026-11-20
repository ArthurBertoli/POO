import os
import sys

# Permite rodar tanto "python Apresentecao/Main.py" quanto "python -m Apresentecao.Main"
# a partir da pasta "Execicio 4": a pasta do exercício precisa estar no sys.path
# para que o pacote "dados" seja encontrado.
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from dados.Aluno import Aluno
from dados.Professor import Professor

aluno1 = Aluno("Javi", [8.5, 9.0, 7.5, 8.0, 9.5])
aluno2 = Aluno("CHRONIC", [6.0, 6.5, 5.5, 7.0, 6.5])
aluno3 = Aluno("Netflixo", [9.0, 9.5, 9.0, 8.5, 9.5])
aluno4 = Aluno("Sabugo", [5.5, 6.0, 6.5, 5.0, 6.0])
aluno5 = Aluno("Aligatore", [7.5, 8.0, 7.0, 8.5, 8.0])

professor1 = Professor("Prof. Silas", 6000.0)
professor2 = Professor("Prof. Costela", 5000.0)

print(aluno1)
print(aluno2)
print(aluno3)
print(aluno4)
print(aluno5)
print(professor1)
print(professor2)
