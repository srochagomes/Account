
CREATE TABLE TB_ACCOUNT (
     ID_KEY UUID PRIMARY KEY,
     ID_APPLICATION TEXT NOT NULL,
     ID_KEY_USER_OWNER UUID  NOT NULL,
     DS_EMAIL TEXT NOT NULL,
     FL_TERM_ACCEPT BOOLEAN NOT NULL,
     DT_HR_CREATED TIMESTAMP NOT NULL,
     EN_STATUS TEXT NOT NULL
);
CREATE INDEX INDEX_ACCOUNT_TO_APPLICATION_OWNER ON TB_ACCOUNT (ID_APPLICATION,DS_EMAIL);

CREATE TABLE TB_USER (
     ID_KEY UUID PRIMARY KEY,
     ID_ACCOUNT UUID NOT NULL,
     DT_HR_CREATED TIMESTAMP NOT NULL,
     DS_EMAIL TEXT NOT NULL,
     NM_USER TEXT NOT NULL,
     FL_TERM_ACCEPT BOOLEAN NOT NULL,
     FL_EMAIL_VERIFIED BOOLEAN NOT NULL,
     EN_STATUS TEXT NOT NULL
);

/*
 CREATE TABLE exemplo (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT,
  campo_integer INTEGER,
  campo_decimal DECIMAL(10,2),
  campo_boolean BOOLEAN,
  campo_data DATE,
  campo_hora TIME,
  campo_datahora TIMESTAMP,
  campo_json JSON,
  campo_array INTEGER[],
  campo_uuid UUID
);

 CREATE TABLE exemplo1 (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT NOT NULL,
  campo_integer INTEGER DEFAULT 0
);

 CREATE TABLE exemplo2 (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT UNIQUE
);
 CREATE TABLE exemplo3 (
  id SERIAL PRIMARY KEY,
  campo_integer INTEGER CHECK (campo_integer >= 0)
);

 CREATE TABLE exemplo4 (
  id SERIAL PRIMARY KEY,
  campo_referencia INTEGER,
  FOREIGN KEY (campo_referencia) REFERENCES outra_tabela(id)
);

 -- Criação da sequência
CREATE SEQUENCE exemplo_seq START 1 INCREMENT 1;

 CREATE TEMPORARY TABLE exemplo_temp (
  id SERIAL PRIMARY KEY,
  campo_texto TEXT
);

 -- Criação de um índice
CREATE INDEX exemplo_index ON exemplo (campo_texto);

 -- Criação de uma visão (view)
CREATE VIEW exemplo_view AS
SELECT id, campo_texto
FROM exemplo
WHERE campo_texto = 'valor';

 -- Criação de uma função
CREATE FUNCTION exemplo_func(parametro INT)
RETURNS INT AS $$
  DECLARE
    resultado INT;
  BEGIN
    resultado := parametro * 2;
    RETURN resultado;
  END;
$$ LANGUAGE plpgsql;
 */