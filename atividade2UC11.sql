create database UC11;

use UC11;

CREATE TABLE produtos (
  id int NOT NULL primary key auto_increment,
  nome varchar(30),
  valor double,
  status varchar(10)
);

INSERT INTO produtos (id, nome, valor, status) VALUES
(1, 'PS4', 1500.00, 'Vendido'),
(2, 'Xbox 360', 800.00, 'Vendido'),
(3, 'Iphone 12', 4800.00, 'Vendido'),
(4, 'PS2', 400.00, 'A Venda');