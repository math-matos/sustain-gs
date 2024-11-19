INSERT INTO EmpresasParceiras (Nome, AreaAtuacao, DataParceria)
VALUES
('SAP', 'Tecnologia', '2024-01-15'),
('FIA Formula E', 'Automobilismo Sustentável', '2023-07-10'),
('Mahindra Formula E Team', 'Mobilidade Elétrica', '2023-09-20'),
('Ultragaz', 'Distribuição de Energia', '2024-03-05'),
('Ultracargo', 'Logística de Energia', '2024-05-18');

INSERT INTO ProjetosEnergeticos (NomeProjeto, TipoEnergia, Custo, DataInicio, DataConclusao, EmpresaID)
VALUES
('Parque Solar Norte', 'Solar', 25000000.00, '2024-02-01', '2025-06-30', 1),
('Energização Urbana Eólica', 'Eólica', 15000000.00, '2023-08-15', '2024-12-31', 2),
('Microgrid Comunitário', 'Solar', 5000000.00, '2024-05-01', NULL, 4);

INSERT INTO ProjecoesSetor (TipoEnergia, PercentualProjecao, AnoProjecao)
VALUES
('Solar', 30.00, 2028),
('Eólica', 21.00, 2028),
('Hidrelétrica', 45.00, 2028);

INSERT INTO ComunidadesImpactadas (NomeComunidade, Localizacao, ProjetoID)
VALUES
('Comunidade Sol Nascente', 'Piauí', 1),
('Vila Ventania', 'Bahia', 2),
('Povoado Esperança', 'Ceará', 3);
