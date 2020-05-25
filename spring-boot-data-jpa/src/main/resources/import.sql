/*Populate tables*/

INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(1, 'Marco', 'Ortega', 'correomarco@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(2, 'Stephi', 'Gómez', 'correostephi@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(3, 'Kevin', 'Roldan', 'kevin@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(4, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(5, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(6, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(7, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(8, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(9, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(10, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(11, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(12, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(13, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(14, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(15, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(16, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(17, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(18, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(19, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(20, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(21, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(22, 'Otro', 'Usuario', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(23, 'Otro', 'Usuario', 'correo@correo.com', '2020-08-02', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(24, 'Otro', 'Gómez', 'correo@correo.com', '2020-06-06', '');
INSERT INTO clients (id, name, last_name, email, create_at, foto) VALUES(25, 'Otro', 'Ortega', 'correo@correo.com', '2020-08-02', '');

/* Puplate product table */
INSERT INTO products (name, price, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony Camara Digital DSC-W320B', 123490, NOW());
INSERT INTO products (name, price, create_at) VALUES('Apple iPod Shuffle', 1499990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Sony NoteBook Z110', 37990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO products (name, price, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creating invoices */
INSERT INTO invoices (description, observation, client_id, create_at) VALUES('Office Equipment Invoice', null, 1, NOW());
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES(1, 1, 1);
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES(2, 1, 4);
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES(1, 1, 5);
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES(1, 1, 7);

INSERT INTO invoices (description, observation, client_id, create_at) VALUES('Bicycle Invoice', 'Some important note!', 1, NOW());
INSERT INTO invoices_items (quantity, invoice_id, product_id) VALUES(3, 2, 6);
