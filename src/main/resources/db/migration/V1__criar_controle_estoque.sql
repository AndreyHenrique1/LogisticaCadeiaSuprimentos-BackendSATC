-- Tabela de controle de estoque do tema Logística e Cadeia de Suprimentos.
-- codigo é gerado automaticamente pelo banco (SERIAL) para o POST não precisar enviá-lo.
CREATE TABLE controle_estoque (
    codigo SERIAL PRIMARY KEY,
    produto VARCHAR(50) NOT NULL,
    estoque_atual INT NOT NULL,
    estoque_minimo INT NOT NULL
);
