ALTER TABLE registros ALTER COLUMN item_id DROP NOT NULL; 
ALTER TABLE registros DROP CONSTRAINT registros_item_id_fkey;
ALTER TABLE registros ADD COLUMN item_nome VARCHAR(255);

ALTER TABLE registros ADD COLUMN funcionario_nome VARCHAR(255);

ALTER TABLE registros ALTER COLUMN quantidade DROP NOT NULL; 