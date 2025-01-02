CREATE DATABASE wm_freela;
USE wm_freela;

CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    quantidade INT NOT NULL,
    categoria_id INT,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES Categoria (id)
);

CREATE TABLE Venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL
);

CREATE TABLE venda_produto (
    venda_id INT NOT NULL,
    produto_id INT NOT NULL,
    PRIMARY KEY (venda_id, produto_id),
    CONSTRAINT fk_venda FOREIGN KEY (venda_id) REFERENCES Venda (id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES Produto (id)
);