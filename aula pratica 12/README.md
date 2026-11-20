# Calculadora Estatística

> Projeto da disciplina **Programação Orientada a Objetos** — Ciência da Computação, UDESC.

## Sobre

Aplicação desktop em Java Swing (aula prática 12). O usuário digita números inteiros e a janela mostra, em tabelas, os valores informados e as estatísticas calculadas sobre eles.

## Funcionalidades

- Adicionar valores inteiros pela caixa de texto (botão **Adicionar** ou tecla Enter) e limpar a lista (botão **Limpar**). Um texto que não seja número inteiro mostra uma mensagem de erro em vez de ser aceito.
- Tabela de resultados com: valor sorteado aleatoriamente da lista (sorteado de novo a cada valor adicionado), somatório, média aritmética, média geométrica, variância (amostral, dividida por *n − 1*), desvio padrão e amplitude.
- Quando a lista está vazia, a tabela de resultados mostra "−".
- Com um único valor, a variância e o desvio padrão aparecem como 0 (a variância amostral precisa de pelo menos dois valores).
- A média geométrica é calculada por logaritmos, sem estourar com muitos valores; é 0 se houver um zero e aparece como "−" se houver valor negativo (não é definida nesse caso).

## Conceitos aplicados

- **Singleton**: `CalculadoraEstatistica.getInstance()` garante uma única instância compartilhada pelas duas tabelas.
- **`AbstractTableModel`**: `TabelaValores` e `TabelaResultados` são modelos de tabela que avisam a interface sobre mudanças com `fireTableDataChanged()`.
- Separação em pacotes por camada: `dados`, `apresentacao` e `main`.

## Tecnologias

- Java (Swing)

## Estrutura

```
aula pratica 12/
├── dados/
│   ├── CalculadoraEstatistica.java   # lógica e cálculos (Singleton)
│   └── ISequencia.java               # interface (vazia)
├── apresentacao/
│   ├── TabelaValores.java            # modelo da tabela de valores
│   └── TabelaResultados.java         # modelo da tabela de resultados
└── main/
    └── Calculadora.java              # janela (JFrame) e método main
```

## Como executar

A partir da pasta `aula pratica 12`:

```bash
javac -encoding UTF-8 -d bin dados/*.java apresentacao/*.java main/*.java
java -cp bin main.Calculadora
```

No Eclipse, crie um projeto Java, copie as pastas `dados`, `apresentacao` e `main` para a pasta `src` e execute a classe `main.Calculadora`.

## Autores

- Arthur Bertoli
