CREATE DATABASE wm_freela;
USE wm_freela;

CREATE TABLE Categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(100) NOT NULL UNIQUE,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade DECIMAL(10, 2) NOT NULL,
    categoria_id BIGINT,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES Categoria(id) ON DELETE SET NULL
);

CREATE TABLE Venda (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	cpf VARCHAR(15)
);

-- Criação da tabela de associação para o relacionamento Many-to-Many entre Venda e Produto
CREATE TABLE venda_produto (
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    PRIMARY KEY (venda_id, produto_id),
    CONSTRAINT fk_venda FOREIGN KEY (venda_id) REFERENCES Venda(id) ON DELETE CASCADE,
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES Produto(id) ON DELETE CASCADE
);