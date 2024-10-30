-- Populando a tabela tb_clients
INSERT IGNORE INTO tb_clients (id, name, cpf, gender, birth_date, active) VALUES
                                                                       (1, 'João Silva', '12345678900', 'MALE', '1985-05-10', true),
                                                                       (2, 'Maria Oliveira', '98765432100', 'FEMALE', '1990-08-15', true),
                                                                       (3, 'Carlos Souza', '12312312399', 'MALE', '1978-11-20', false),
                                                                       (4, 'Ana Santos', '32132132177', 'FEMALE', '2000-04-05', true);

-- Populando a tabela tb_products
INSERT IGNORE INTO tb_products (id, name, brand, manufacturing_date, expiration_date, category, active) VALUES
                                                                        (1, 'Perfume', 'MarcaX', '2023-01-01', '2024-01-01', 'COSMETICS', true),
                                                                        (2, 'Condicionador', 'MarcaY', '2023-02-01', '2024-02-01', 'PERSONAL_CARE', true),
                                                                        (3, 'Arroz', 'MarcaZ', '2023-03-15', '2024-02-01', 'FOOD', true),
                                                                        (4, 'Cereal', 'MarcaA', '2023-07-01', '2024-07-01', 'FOOD', true),
                                                                        (5, 'Detergente', 'MarcaV', '2023-05-01', '2025-05-01', 'CLEANING', true);
