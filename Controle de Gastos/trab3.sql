-- Banco de dados do Controle de Gastos (PostgreSQL).
--
-- Como usar (exemplo com o usuário postgres):
--   createdb -U postgres trab3
--   psql -U postgres -d trab3 -f trab3.sql
--
-- O programa também executa estes mesmos comandos (CREATE TABLE IF NOT EXISTS) ao conectar,
-- então basta que o banco "trab3" exista.
--
-- Atenção: versões antigas deste script criavam a tabela gasto com a coluna "date" e sem
-- "usuario_cpf". Se você criou as tabelas com a versão antiga, apague-as antes:
--   DROP TABLE IF EXISTS gasto; DROP TABLE IF EXISTS usuarios;

CREATE TABLE IF NOT EXISTS usuarios (
	id serial PRIMARY KEY,
	cpf varchar(11) NOT NULL UNIQUE,
	nome varchar(100) NOT NULL,
	email varchar(100),
	senha varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS gasto (
	id serial PRIMARY KEY,
	usuario_cpf varchar(11) NOT NULL REFERENCES usuarios (cpf) ON DELETE CASCADE,
	nome varchar(100) NOT NULL,
	data date NOT NULL,
	descricao varchar(255) NOT NULL,
	valor double precision NOT NULL CHECK (valor > 0),
	categoria varchar(50) NOT NULL,
	mes varchar(20)
);
