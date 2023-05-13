CREATE DATABASE clinica_veterinaria;

USE clinica_veterinaria;

CREATE TABLE veterinario (
    ID_veterinario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    especialidad VARCHAR(255) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido_uno VARCHAR(255) NOT NULL,
    apellido_dos VARCHAR(255) NOT NULL,
    DNI VARCHAR(9) UNIQUE,
    fecha_nac DATE NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    cp INT NOT NULL,
    telefono INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    turno ENUM('M','T','N') NOT NULL    
);

CREATE TABLE auxiliar (
   ID_auxiliar INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    especialidad VARCHAR(255) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido_uno VARCHAR(255) NOT NULL,
    apellido_dos VARCHAR(255) NOT NULL,
    DNI VARCHAR(9) UNIQUE,
    fecha_nac DATE NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    cp INT NOT NULL,
    telefono INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    turno ENUM('M','T','N') NOT NULL
);

CREATE TABLE cliente (
ID_cliente INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
seguro VARCHAR(255) NOT NULL,
DNI VARCHAR(9) UNIQUE,
nombre VARCHAR(255) NOT NULL,
apellido_uno VARCHAR(255) NOT NULL,
apellido_dos VARCHAR(255) NOT NULL,
fecha_nac DATE NOT NULL,
telefono INT NOT NULL,
direccion VARCHAR(255) NOT NULL,
cp INT NOT NULL,
poblacion VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
suscripcion ENUM('S','N') NOT NULL
);


CREATE TABLE mascota (
    ID_mascota INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    sexo ENUM('M','F') NOT NULL,
    fecha_nac DATE NOT NULL,
    raza VARCHAR(255) NOT NULL,
    especie VARCHAR(255) NOT NULL,
    DNI_cliente VARCHAR(9) NOT NULL,
    CONSTRAINT masc_dni_cli_FK FOREIGN KEY (DNI_cliente) REFERENCES cliente(DNI) ON DELETE CASCADE);

CREATE TABLE recepcionista (
    ID_recepcionista INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    salario DECIMAL(10,2) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido_uno VARCHAR(255) NOT NULL,
    apellido_dos VARCHAR(255) NOT NULL,
    DNI VARCHAR(9) UNIQUE,
    fecha_nac DATE NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    cp INT NOT NULL,
    telefono INT NOT NULL,
    email VARCHAR(255) NOT NULL,
    turno ENUM('M','T','N') NOT NULL
);


CREATE TABLE cita (
    ID_cita INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ID_mascota INT NOT NULL,
    ID_veterinario INT NOT NULL,
    ID_cliente INT NOT NULL,
    ID_recepcionista INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    tipo_consulta VARCHAR(255) NOT NULL,
	diagnostico VARCHAR(255) NOT NULL,
	tratamiento VARCHAR(255) NOT NULL,
	medicacion VARCHAR(255) NOT NULL,
	precio DECIMAL(10,2) NOT NULL,
    CONSTRAINT cita_id_mas_FK FOREIGN KEY (ID_mascota) REFERENCES mascota(ID_mascota) ON DELETE CASCADE,
    CONSTRAINT cita_id_vet_FK FOREIGN KEY (ID_veterinario) REFERENCES veterinario(ID_veterinario) ON DELETE CASCADE,
    CONSTRAINT cita_id_cli_FK FOREIGN KEY (ID_cliente) REFERENCES cliente(ID_cliente) ON DELETE CASCADE,
 CONSTRAINT cita_id_recep_FK FOREIGN KEY (ID_recepcionista) REFERENCES recepcionista(ID_recepcionista) ON DELETE CASCADE,
UNIQUE (fecha,hora)
);


