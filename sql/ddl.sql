CREATE DATABASE EnergiaSustentavel;

USE EnergiaSustentavel;

CREATE TABLE EmpresasParceiras (
    EmpresaID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    AreaAtuacao VARCHAR(100),
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
    FOREIGN KEY (EmpresaID) REFERENCES EmpresasParceiras(EmpresaID)
);

CREATE TABLE ProjecoesSetor (
    ProjecaoID INT AUTO_INCREMENT PRIMARY KEY,
    TipoEnergia VARCHAR(50), -- Ex: Solar, Eólica
    PercentualProjecao DECIMAL(5, 2),
    AnoProjecao INT
);

CREATE TABLE ComunidadesImpactadas (
    ComunidadeID INT AUTO_INCREMENT PRIMARY KEY,
    NomeComunidade VARCHAR(100),
    Localizacao VARCHAR(100),
    ProjetoID INT,
    FOREIGN KEY (ProjetoID) REFERENCES ProjetosEnergeticos(ProjetoID)
);
