/* Datos tabla veterinario*/
INSERT INTO veterinario (especialidad, salario, nombre, apellido_uno, apellido_dos, DNI, fecha_nac ,direccion, cp, telefono, email, turno) VALUES 
('Cardiología', 3000.00, 'Juan', 'Pérez', 'García', '20210458G' ,'1986-02-11', 'Calle Salsa 12', 29010, 612326598, 'juan.perez@gmail.com', 'M'),
('Cirugía', 4000.00, 'María', 'Rodriguez', 'Fernández',  '65789456S', '1991-04-07', 'Calle Albarrán 17', 29014, 698154205, 'maria.rofer@gmail.com', 'T'),
('Dermatología', 4500.00, 'Pablo', 'Gómez', 'Vázquez',  '12789456P','1981-09-11','Calle Alcalde Nicolás 23', 29020, 660001122, 'pagovaz@gmail.com', 'N'),
('General', 3000.00, 'Cristina', 'Martín', 'Hidalgo',  '32654987V','1979-10-12', 'Calle Teba 13', 29010, 630128460, 'cristina.martin@gmail.com', 'M'),
('Traumatología', 3300.00, 'Javier', 'Benitez', 'Martín',  '56123458R','1976-05-23','Calle Ilusionista 3', 29001, 667541207, 'javierbemar@gmail.com', 'T'),
('General', 6000.00, 'Ana', 'Williams', 'Fernández',  '95154201F','1983-06-01', 'Calle Andrómeda 16', 29010, 666002578, 'anawifer@gmail.com', 'N');

/* Datos tabla auxiliar*/
INSERT INTO auxiliar (especialidad, salario, nombre, apellido_uno, apellido_dos, DNI, fecha_nac, direccion, cp, telefono, email, turno) VALUES 

('General', 2000.00, 'Jacinto', 'López', 'García',  '20451201S','1996-07-01', 'Calle Jaboneros 23', 29012, 695204512, 'jacinto.lopez@gmail.com', 'M'),
('Limpieza utensilios', 1500.00, 'Marisol', 'Sánchez', 'Callejón', '02151054F', '1998-01-01', 'Calle Aguadulce 4', 29000, 666314902, 'marisol.sanchez@gmail.com', 'T'),
('Curas cutáneas', 2000.00, 'Pedro', 'Gómez', 'Pérez', '76152100H','1992-12-21',  'Calle Llobregat 1', 29100, 666452102, 'pedro.gomez@gmail.com', 'N'),
('General', 1500.00, 'María José' ,'Maeste', 'Gaspar',  '61201457D','1989-05-14', 'Calle Jardin botánico 43', 29030, 660279405, 'majo.maeste@gmail.com', 'M');


/* Datos tabla recepcionista*/       
INSERT INTO recepcionista (salario, nombre, apellido_uno, apellido_dos, DNI, fecha_nac, direccion, cp, telefono, email, turno) VALUES 

(1800.00, 'Óscar', 'Pérez', 'Gallardo', '25456789L',  '1996-06-10', 'Avenida Teatinos 13', 29010, 660612244, 'oscarpe@gmail.com', 'M'),
(2000.00, 'Asunción', 'Sánchez', 'Sánchez', '01246789L' , '1998-03-01', 'Calle Valverde 3', 29015, 666554213, 'asun.sanchez@gmail.com', 'T'),
(1500.00, 'Carlota', 'Gutierrez', 'Pérez', '84321650N', '1992-12-25', 'Calle Campamento 22', 29003, 667221597, 'carlotagu@gmail.com','N');


/* Datos tabla cliente*/       
INSERT INTO cliente (seguro, DNI, nombre, apellido_uno, apellido_dos, fecha_nac, telefono, direccion, cp, poblacion, email, suscripcion) VALUES 

( 'ASISA', '12345678A', 'Emilio', 'Díaz', 'Santillana',  '1986-08-10', 608120462, 'Calle Aguadulce 13', 29100, 'Rincón de la Victoria', 'emidisan@gmail.com', 'S'),
('MAPFRE', '23456789G', 'María', 'Cáceres', 'Badajoz',  '1991-02-19', 715204592, 'Avenida La Pazl 12', 29010, 'Málaga', 'maria.caceres@gmail.com', 'N'),
('SANITAS', '34567260J', 'Peter', 'Buckley', '-','1981-07-18', 660785312, 'Calle Jazmín 8', 29120, 'Benalmádena', 'petbuckley.gomez@gmail.com', 'S'),
('ADESLAS', '45631084M', 'Alejandro', 'Leiva', 'García', '1979-10-01', 619782310, 'Urbanización el Rastrillo 1', 29017, 'Málaga', 'ale.leiva@gmail.com', 'N'),
('DKV', '56789012O', 'Jorge', 'Smith', 'Martín', '1976-02-09', 660045025, 'Calle Campamento 21', 29005, 'Málaga', 'jorge.smith@gmail.com', 'S'),
('AXA', '25745024L', 'Estefania', 'Aguilera', 'Castillo', '1983-03-11', 660651248, 'Calle Ferrandis 30', 29400, 'Alhaurín de la Torre', 'esteaguicas@gmail.com', 'N');


/* Datos tabla mascota*/       
INSERT INTO mascota(nombre, sexo, especie, fecha_nac, raza, DNI_cliente) VALUES 
('Bobby', 'M', 'Perro', '2019-01-01', 'Golden Retriever','12345678A'),
('Bella', 'F',  'Gato', '2020-01-01', 'Siames','23456789G'),
('Lola', 'F', 'Perro', '2021-01-01', 'Bulldog', '34567260J'),
('Perlita', 'M', 'Ave', '2017-01-01', 'Agapornis', '45631084M'),
('Manuelita', 'F',  'Reptil', '2019-01-01', 'Tortuga califorrnia', '56789012O'),
('Sonic', 'M',  'Perro', '2021-07-08', 'Bichón maltés', '25745024L');


/* Datos tabla cita*/   
INSERT INTO cita (ID_mascota, ID_veterinario,ID_cliente, ID_recepcionista ,fecha, hora, tipo_consulta, diagnostico, tratamiento, medicacion, precio) VALUES
(1,1,4, 1,"2022-04-21", "10:00", "Consulta de rutina", "Ninguno", "Ninguno", "Ninguno", 25.50),
(2,2,3,2, "2022-03-02", "14:00", "Cirugía", "Extirpación de verruga", "Cirugía", "Anestesia", 350.00),
(3,3,6, 3,"2023-01-03", "09:00", "Vacunación", "Vacunación anual", "Vacuna", "Ninguno", 30.50),
(4,4,5, 2,"2023-01-04", "11:00", "Examen de sangre", "Anemia", "Transfusión de sangre", "Ninguno", 95.00),
(5,5,1,3, "2022-04-15", "15:00", "Examen de orina", "Infección urinaria", "Antibióticos", "Amoxicilina", 80.00),
(6,6,2, 1,"2022-03-06", "08:00", "Consulta de rutina", "Ingestión de objeto extraño", "Ninguno", "Ninguno", 75.00);

