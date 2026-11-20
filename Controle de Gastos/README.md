# Controle de Gastos

> Projeto da disciplina **Programação Orientada a Objetos** — Ciência da Computação, UDESC.

## Sobre

Aplicação desktop em Java Swing para registrar e acompanhar gastos pessoais. O usuário cria uma conta, faz login com CPF e senha e cadastra gastos com nome, data, descrição, valor e categoria, que podem ser consultados em tabela, filtrados, alterados e visualizados em gráficos feitos com JFreeChart.

Na versão atual, usuários e gastos ficam apenas em memória (listas na classe `Sistema`) e se perdem ao fechar o programa. O script `trab3.sql` e o código de conexão com PostgreSQL existem, mas a integração está comentada no código e não é usada.

## Funcionalidades

- Cadastro de usuário (CPF, nome, e-mail e senha) e login por CPF e senha.
- Cadastro de gastos com validação de campos obrigatórios, do valor numérico e da data no formato `dd/MM/yyyy`.
- Categorias fixas: Comida, Lazer, Educação, Saúde, Transporte e Outros.
- Tabela de gastos com filtro combinado por mês e categoria.
- Alteração de um gasto selecionado em uma tabela.
- Gráfico de barras com os valores por categoria, agrupados por mês.
- Gráfico de dispersão "anual", em que o usuário informa o valor de cada um dos 12 meses em caixas de diálogo.

## Tecnologias

- Java 17 (projeto Eclipse)
- Swing (interface gráfica)
- JFreeChart 1.0.0 e JCommon 1.0.0 (gráficos)
- PostgreSQL (somente o script `trab3.sql`; a conexão está desativada no código)

## Estrutura

```
Controle de Gastos/
├── trab3.sql                    # criação das tabelas gasto e usuarios no PostgreSQL
└── Trabalho2/Trabalho2/src/
    ├── dados/                   # Gasto e Usuario (modelos)
    ├── negocio/Sistema.java     # cadastro, autenticação, filtros e alteração de gastos
    ├── view/                    # telas Swing
    │   ├── PrimeiraTela.java    # login (contém o main)
    │   ├── TelaCadastro.java    # cadastro de usuário
    │   ├── SegundaTela.java     # cadastro de gastos e menu principal
    │   ├── TabelaGastos.java    # tabela com filtros
    │   ├── AlterarGastoTabela.java / AlterarGasto.java  # seleção e edição de gasto
    │   └── TelaGraficoBarra.java / Scatter.java         # gráficos
    ├── Exceptions/              # exceções de conexão com o banco
    └── Conexao/ConexaoDAO.java  # esboço de conexão (todo comentado)
```

## Como executar

1. Importe a pasta `Trabalho2/Trabalho2` no Eclipse como projeto Java existente.
2. Adicione `jfreechart-1.0.0.jar` e `jcommon-1.0.0.jar` ao Build Path. O `.classpath` original aponta para caminhos de outra máquina, então é preciso corrigi-los.
3. Execute a classe `view.PrimeiraTela`.
4. Clique em **Cadastrar** para criar um usuário e depois faça login com o CPF e a senha cadastrados.

## Autores

Arthur Bertoli
