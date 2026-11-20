# Calculadora Estatística

> Projeto da disciplina **Programação Orientada a Objetos** — Ciência da Computação, UDESC.

## Sobre

Aplicação desktop em Java Swing (aula prática 12). O usuário digita números inteiros e a janela mostra, em tabelas, os valores informados e as estatísticas calculadas sobre eles.

## Funcionalidades

- Adicionar valores inteiros pela caixa de texto (botão **Adicionar**) e limpar a lista (botão **Limpar**).
- Tabela de resultados com: valor sorteado aleatoriamente da lista, somatório, média aritmética, média geométrica, variância (amostral, dividida por *n − 1*), desvio padrão e amplitude.
- Quando a lista está vazia, a tabela de resultados mostra "−".

## Conceitos aplicados

- **Singleton**: `CalculadoraEstatistica.getInstance()` garante uma única instância compartilhada pelas duas tabelas.
- **`AbstractTableModel`**: `TabelaValores` e `TabelaResultados` são modelos de tabela que avisam a interface sobre mudanças com `fireTableStructureChanged()`.
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
javac -d bin dados/*.java apresentacao/*.java main/*.java
java -cp bin main.Calculadora
```

## Autores

- Arthur Bertoli
