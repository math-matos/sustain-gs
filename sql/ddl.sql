CREATE DATABASE EnergiaSustentavel;

USE EnergiaSustentavel;

CREATE TABLE EmpresasParceiras (
    EmpresaID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    AreaAtuacao VARCHAR(100),
    CNPJ VARCHAR(18) NOT NULL UNIQUE,
    Senha VARCHAR(255) NOT NULL,
    DataParceria DATE
);

CREATE TABLE ProjetosEnergeticos (
    ProjetoID INT AUTO_INCREMENT PRIMARY KEY,
    NomeProjeto VARCHAR(150) NOT NULL,
    TipoEnergia VARCHAR(50) NOT NULL, -- Ex: Solar, Eólica, Nuclear
    Custo DECIMAL(15, 2),
    DataInicio DATE,
    DataConclusao DATE,
    EmpresaID INT,
    FOREIGN KEY (EmpresaID) REFERENCES EmpresasParceiras (EmpresaID)
);

CREATE TABLE Usuarios (
    UsuarioID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    CPF VARCHAR(14) NOT NULL UNIQUE,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Senha VARCHAR(255) NOT NULL
);