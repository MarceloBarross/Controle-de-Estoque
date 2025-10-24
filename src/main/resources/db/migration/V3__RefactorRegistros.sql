DROP TABLE IF EXISTS registros;

CREATE TABLE if NOT EXISTS registros(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tabela_afetada VARCHAR(100) NOT NULL,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('ENTRADA', 'SAIDA', 'CRIACAO', 'ALTERACAO', 'EXCLUSAO')),
    id_registro_afetado UUID NOT NULL,
    valor_anterior VARCHAR,          
    valor_novo VARCHAR,              
    id_usuario UUID NOT NULL,
    usuario VARCHAR(100),
    data_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);