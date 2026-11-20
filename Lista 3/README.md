# Lista 3: Herança e Classes Abstratas

> Projeto da disciplina **Programação Orientada a Objetos** — Ciência da Computação, UDESC.

## Sobre

Resolução da Lista 3 (2022.2) sobre herança e classes abstratas. O enunciado está em `../2022.2 - Lista 3 - Herança e Classes Abstratas.pdf`. Os exercícios 1 a 3 foram feitos em Java e o exercício 4 em Python.

## Exercícios

1. **Animais**: classe abstrata `Animal`, com atributo `nome` e método abstrato `emitirSom()`, e as subclasses `Crocodilo`, `Coruja` e `Beluga`. O `main` cria dois objetos de cada classe e imprime o nome e o som de cada um.
2. **Formas geométricas**: classe abstrata `FormaGeometrica`, com duas medidas e os métodos abstratos `calculaArea()` e `calculaPerimetro()`, e as subclasses `TrianguloEquilatero`, `Losango` e `Circulo`, cada uma com o próprio `toString()` mostrando dimensões, área e perímetro. Os resultados são inteiros arredondados; o perímetro do losango é `4 × √((D/2)² + (d/2)²)` e o círculo usa `Math.PI`.
3. **Grafos**: `Digrafo` representa a matriz de adjacência com `List<List<Integer>>` e tem os métodos `adicionarVertice()`, `adicionarAresta(origem, destino)` e `toString()`. `Grafo` estende `Digrafo` e sobrescreve `adicionarAresta` para manter a matriz simétrica.
4. **Pessoas (Python)**: classe abstrata `Pessoa` (`abc.ABC`, com `__repr__` abstrato) e as subclasses `Aluno`, que tem notas, `calcularMedia()` e status "Aprovado" (média ≥ 7) ou "Em exame", e `Professor`, que tem salário. As duas sobrescrevem `__repr__`.

## Tecnologias

- Java (exercícios 1 a 3)
- Python 3 (exercício 4)

## Estrutura

```
Lista 3/
├── Exercicio 1/   # apresentacao/Main.java, dados/{Animal, Beluga, Coruja, Crocodilo}.java
├── Exercicio 2/   # apresentacao/Main.java, dados/{FormaGeometrica, Circulo, Losango, TrianguloEquilatero}.java
├── Exercicio 3/   # apresentacao/Main.java, dados/{Digrafo, Grafo}.java
└── Execicio 4/    # Apresentecao/Main.py, dados/{Pessoa, Aluno, Professor}.py
```

## Como executar

Exercícios em Java (exemplo com o Exercício 1; os outros seguem o mesmo padrão):

```bash
cd "Exercicio 1"
javac -encoding UTF-8 -d bin dados/*.java apresentacao/*.java
java -cp bin apresentacao.Main
```

Cada exercício é compilado separadamente, porque todos usam os mesmos nomes de pacote (`dados` e `apresentacao`). No Eclipse, crie um projeto por exercício, copie as pastas `dados` e `apresentacao` para `src` e execute `apresentacao.Main`.

Exercício 4 (Python 3), a partir da pasta `Execicio 4`:

```bash
cd "Execicio 4"
python Apresentecao/Main.py
```

Também funciona `python -m Apresentecao.Main`, na mesma pasta.

## Autores

- Arthur Bertoli
