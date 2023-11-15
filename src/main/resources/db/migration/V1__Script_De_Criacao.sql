CREATE TABLE IF NOT EXISTS Ingrediente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL,
    preco DOUBLE
);

CREATE TABLE IF NOT EXISTS Lanche (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL,
    preco DOUBLE,
    FOREIGN KEY (id) REFERENCES Ingrediente (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Promocao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) UNIQUE NOT NULL,
    descricao VARCHAR(255),
    preco DOUBLE,
    percentualDesconto DOUBLE,
    FOREIGN KEY (id) REFERENCES Lanche (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lanche_ingrediente (
    lanche_id BIGINT,
    ingrediente_id BIGINT,
    PRIMARY KEY (lanche_id, ingrediente_id),
    FOREIGN KEY (lanche_id) REFERENCES Lanche (id) ON DELETE CASCADE,
    FOREIGN KEY (ingrediente_id) REFERENCES Ingrediente (id) ON DELETE CASCADE
);

INSERT INTO Ingrediente (nome, preco) VALUES ('Pão', 2.0);