CREATE TABLE IF NOT EXISTS itens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    quantidade INT NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS funcionarios(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(20)
);
CREATE TABLE if not EXISTS registros(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    data_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('ENTRADA', 'SAIDA', 'CRIACAO', 'ALTERACAO', 'EXCLUSAO')),
    quantidade INT NOT NULL DEFAULT 1,
    item_id UUID NOT NULL,
    funcionario_id UUID NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
    FOREIGN KEY (item_id) REFERENCES itens(id)
);