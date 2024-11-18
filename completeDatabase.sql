CREATE TABLE Produtos (
    ProdutoID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100) NOT NULL,
    Preco DECIMAL(10, 2) NOT NULL,
    Estoque INT NOT NULL
);

INSERT INTO Produtos (Nome, Preco, Estoque)
VALUES ('Notebook', 3500.00, 10),
       ('Mouse', 50.00, 100),
       ('Teclado', 150.00, 50);

SELECT * FROM Produtos;

UPDATE Produtos
SET Preco = 60.00
WHERE Nome = 'Mouse';

DELETE FROM Produtos
WHERE Estoque = 0;
