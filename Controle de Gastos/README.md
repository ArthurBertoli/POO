# Controle de Gastos

> Projeto da disciplina **Programação Orientada a Objetos** — Ciência da Computação, UDESC.

## Sobre

Aplicação desktop em Java Swing para registrar e acompanhar gastos pessoais. O usuário cria uma conta, faz login com CPF e senha e cadastra gastos com nome, data, descrição, valor e categoria, que podem ser consultados em tabela, filtrados, alterados e visualizados em gráficos feitos com JFreeChart.

Os dados podem ser guardados em um banco **PostgreSQL** (modo com banco) ou apenas na memória (modo memória). O programa escolhe sozinho: se o banco estiver configurado e acessível, usa o banco; senão, avisa o usuário e continua funcionando em memória.

## Funcionalidades

- Cadastro de usuário (CPF, nome, e-mail e senha) e login por CPF e senha. O CPF pode ser digitado com ou sem pontuação, mas precisa ter 11 dígitos, e não é possível cadastrar o mesmo CPF duas vezes.
- Cada usuário vê apenas os próprios gastos.
- Cadastro de gastos com validação de campos obrigatórios, do valor (número maior que zero; aceita vírgula ou ponto) e da data no formato `dd/MM/yyyy` (datas inexistentes, como 31/02, são recusadas).
- Categorias fixas: Comida, Lazer, Educação, Saúde, Transporte e Outros.
- Tabela de gastos com filtro por mês e por categoria (cada filtro tem a opção "todos").
- Alteração de um gasto selecionado em uma tabela.
- Gráfico de barras com o total por categoria em cada mês.
- Gráfico de dispersão "anual" com o total gasto em cada mês, uma série por ano, calculado a partir dos gastos cadastrados.

## Tecnologias

- Java 17 ou superior (projeto Eclipse)
- Swing (interface gráfica)
- JFreeChart 1.0.0 e JCommon 1.0.0 (gráficos), já incluídos em `Trabalho2/Trabalho2/lib`
- PostgreSQL + driver JDBC (opcional; veja "Modo com banco")

## Estrutura

```
Controle de Gastos/
├── trab3.sql                        # criação das tabelas usuarios e gasto no PostgreSQL
└── Trabalho2/Trabalho2/             # projeto Eclipse
    ├── db.properties                # configuração do banco (url, usuário, senha)
    ├── lib/                         # jcommon-1.0.0.jar e jfreechart-1.0.0.jar (e o driver do PostgreSQL, se você colocar)
    └── src/
        ├── dados/                   # Gasto e Usuario (modelos)
        ├── negocio/Sistema.java     # cadastro, autenticação, filtros, alteração e escolha banco/memória
        ├── Conexao/                 # ConexaoDAO (lê db.properties e conecta), UsuarioDAO e GastoDAO
        ├── Exceptions/              # exceções de conexão com o banco
        └── view/                    # telas Swing
            ├── PrimeiraTela.java    # login (contém o main)
            ├── TelaCadastro.java    # cadastro de usuário
            ├── SegundaTela.java     # cadastro de gastos e menu principal
            ├── TabelaGastos.java    # tabela com filtros
            ├── AlterarGastoTabela.java / AlterarGasto.java  # seleção e edição de gasto
            └── TelaGraficoBarra.java / Scatter.java         # gráficos
```

## Como executar

### Pelo terminal

Na pasta `Trabalho2/Trabalho2` (Windows, PowerShell ou Prompt de Comando):

```bash
javac -encoding UTF-8 -d bin -cp "lib/*" src/Conexao/*.java src/Exceptions/*.java src/dados/*.java src/negocio/*.java src/view/*.java
java -cp "bin;lib/*" view.PrimeiraTela
```

No Linux ou macOS, troque o `;` do classpath por `:` (`java -cp "bin:lib/*" view.PrimeiraTela`).

O `lib/*` coloca no classpath todos os JARs da pasta `lib`, inclusive o driver do PostgreSQL, se ele estiver lá.

### Pelo Eclipse

1. **File > Import > Existing Projects into Workspace** e selecione a pasta `Trabalho2/Trabalho2`.
2. O `.classpath` já aponta para `lib/jcommon-1.0.0.jar` e `lib/jfreechart-1.0.0.jar` com caminhos relativos; não é preciso configurar nada para o modo memória.
3. Execute a classe `view.PrimeiraTela`.
4. Clique em **Cadastrar** para criar um usuário e depois faça login com o CPF e a senha cadastrados.

## Modo memória e modo com banco

### Modo memória

É o que acontece logo depois de clonar: sem o driver do PostgreSQL (ou sem banco rodando), o programa mostra um aviso ao abrir e funciona normalmente, mas usuários e gastos ficam só na memória e se perdem ao fechar. O título da janela de login indica o modo em uso ("modo memória" ou "PostgreSQL").

Para usar o modo memória sem o aviso, coloque `db.enabled=false` em `db.properties`.

### Modo com banco (PostgreSQL)

1. Crie o banco e as tabelas:

   ```bash
   createdb -U postgres trab3
   psql -U postgres -d trab3 -f trab3.sql
   ```

   O programa também cria as tabelas automaticamente (`CREATE TABLE IF NOT EXISTS`) ao conectar, então basta que o banco exista. Se você criou as tabelas com uma versão antiga do `trab3.sql`, apague-as antes (veja o comentário no início do script).

2. Baixe o driver JDBC do PostgreSQL (arquivo `postgresql-<versão>.jar`, disponível em https://jdbc.postgresql.org) e coloque-o em `Trabalho2/Trabalho2/lib/`, por exemplo `lib/postgresql-42.7.4.jar`. O driver não vem no repositório.
   - Pelo terminal, o `-cp "bin;lib/*"` já inclui o driver.
   - No Eclipse, adicione o JAR em **Build Path > Add JARs...** (o `.classpath` não o referencia porque o arquivo não existe no repositório).

3. Edite `Trabalho2/Trabalho2/db.properties`:

   ```properties
   db.enabled=true
   db.url=jdbc:postgresql://localhost:5432/trab3
   db.user=postgres
   db.password=SUA_SENHA_AQUI
   db.timeout=5
   ```

   O valor de `db.password` no repositório é só um exemplo: troque pela sua senha localmente e **não** faça commit da senha real.

O arquivo `db.properties` é procurado na pasta de trabalho atual e na pasta do projeto (a pasta acima de `bin`). Também é possível indicar outro arquivo com `java -Ddb.config=caminho/para/db.properties -cp "bin;lib/*" view.PrimeiraTela`.

Se o driver não estiver no classpath, se a conexão falhar (banco desligado, senha errada etc.) ou se uma gravação der erro durante o uso, o programa mostra um aviso com o motivo e continua funcionando em modo memória, sem travar. A tentativa de conexão espera no máximo `db.timeout` segundos.

## Autores

Arthur Bertoli
