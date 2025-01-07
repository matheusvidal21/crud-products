-- Insert initial data into the `tb_clients` table
INSERT IGNORE INTO tb_clients (name, cpf, gender, birth_date, active) VALUES
                                                                   ('Alice Smith', '12345678901', 'FEMALE', '1990-05-12', true),
                                                                   ('John Doe', '23456789012', 'MALE', '1985-03-24', true),
                                                                   ('Emma Johnson', '34567890123', 'FEMALE', '1993-07-19', true);

-- Insert initial data into the `tb_products` table
INSERT IGNORE INTO tb_products (name, brand, manufacturing_date, expiration_date, category, active) VALUES
                                                                                                 ('Shampoo', 'BrandA', '2023-01-01', '2025-01-01', 'PERSONAL_CARE', true),
                                                                                                 ('Soap', 'BrandB', '2023-02-15', '2024-02-15', 'CLEANING', true),
                                                                                                 ('Pasta', 'BrandC', '2023-03-10', '2024-03-10', 'FOOD', true),
                                                                                                 ('Lipstick', 'BrandD', '2023-04-05', '2024-04-05', 'COSMETICS', true);

-- Insert initial data into the `tb_orders` table
-- The `client_id` is referenced from the `tb_clients` table
INSERT IGNORE INTO tb_orders (codigo, client_id, active) VALUES
                                                      ('ORD123', 1, true), -- Alice Smith
                                                      ('ORD124', 2, true), -- John Doe
                                                      ('ORD125', 3, true); -- Emma Johnson

-- Insert initial data into the `order_products` table (association table)
-- The `order_id` is referenced from `tb_orders`
-- The `product_id` is referenced from `tb_products`
INSERT IGNORE INTO order_products (order_id, product_id) VALUES
                                                      (1, 1), -- Alice ordered Shampoo
                                                      (1, 2), -- Alice ordered Soap
                                                      (2, 3), -- John ordered Pasta
                                                      (3, 4); -- Emma ordered Lipstick


-- Insert initial roles into the `tb_roles` table (if not already present)
INSERT IGNORE INTO tb_roles (role_name) VALUES
                                 ('ROLE_ADMIN'),
                                 ('ROLE_USER');

-- Insert initial data into the `tb_users_roles` table to associate the admin user with the 'ROLE_ADMIN' role
INSERT IGNORE INTO tb_users_roles (user_id, role_id)
SELECT u.id, r.id
FROM tb_users u
         JOIN tb_roles r ON r.role_name = 'ROLE_ADMIN'
WHERE u.username = 'admin';
