INSERT INTO EmpresasParceiras (Nome, AreaAtuacao, CNPJ, Senha, DataParceria)
VALUES
('SAP', 'Tecnologia', '12.345.678/0001-90', 'senhaSegura123', '2024-01-15'),
('FIA Formula E', 'Automobilismo Sustentável', '98.765.432/0002-10', 'senhaFormulaE456', '2023-07-10'),
('Mahindra Formula E Team', 'Mobilidade Elétrica', '11.222.333/0003-44', 'senhaMahindra789', '2023-09-20'),
('Ultragaz', 'Distribuição de Energia', '55.666.777/0004-55', 'senhaUltragaz123', '2024-03-05'),
('Ultracargo', 'Logística de Energia', '88.999.000/0005-66', 'senhaUltracargo456', '2024-05-18');

INSERT INTO ProjetosEnergeticos (NomeProjeto, TipoEnergia, Custo, DataInicio, DataConclusao, EmpresaID)
VALUES
('Parque Solar Norte', 'Solar', 25000000.00, '2024-02-01', '2025-06-30', 1),
('Energização Urbana Eólica', 'Eólica', 15000000.00, '2023-08-15', '2024-12-31', 2),
('Microgrid Comunitário', 'Solar', 5000000.00, '2024-05-01', NULL, 4);

INSERT INTO Usuarios (Nome, CPF, Email, Senha)
VALUES
('João Silva', '123.456.789-00', 'joao.silva@email.com', 'senhaJoao123'),
('Maria Oliveira', '987.654.321-11', 'maria.oliveira@email.com', 'senhaMaria456'),
('Carlos Souza', '111.222.333-44', 'carlos.souza@email.com', 'senhaCarlos789');
