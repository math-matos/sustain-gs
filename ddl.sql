CREATE DATABASE MeuBanco;

USE MeuBanco;

CREATE TABLE Clientes (
    ClienteID INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    DataNascimento DATE
);

ALTER TABLE Clientes ADD Telefone VARCHAR(15);

DROP TABLE Clientes;
