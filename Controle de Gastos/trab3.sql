CREATE TABLE gasto (
	id serial PRIMARY KEY,
	nome varchar(50),
	date timestamp,
	descricao varchar(150),
	valor double precision,
	categoria varchar(150),
	mes varchar(20)
);

CREATE TABLE usuarios (
	id serial PRIMARY KEY,
	cpf varchar(50),
	nome varchar(50),
	email varchar(50),
	senha varchar(50)
);

SELECT usename FROM pg_user;
