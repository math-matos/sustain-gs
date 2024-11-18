INSERT INTO Clientes (Nome, Email, DataNascimento, Telefone)
VALUES ('João Silva', 'joao@email.com', '1990-05-15', '11999999999');

SELECT * FROM Clientes;

UPDATE Clientes
SET Telefone = '11988888888'
WHERE ClienteID = 1;

DELETE FROM Clientes
WHERE ClienteID = 1;
