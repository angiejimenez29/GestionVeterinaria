CREATE USER 'director'@'localhost' IDENTIFIED BY 'director1234';
GRANT ALL PRIVILEGES ON clinica_veterinaria.* TO 'director'@'localhost'  WITH GRANT OPTION;

CREATE USER 'veterinario'@'localhost' IDENTIFIED BY 'veterinario1234';
GRANT ALL ON clinica_veterinaria.mascota  TO 'veterinario'@'localhost';
GRANT ALL ON clinica_veterinaria.cita TO 'veterinario'@'localhost';


CREATE USER 'recepcionista'@'localhost' IDENTIFIED BY 'recepcionista1234';
GRANT ALL ON clinica_veterinaria.cliente TO 'recepcionista'@'localhost';
GRANT ALL ON clinica_veterinaria.cita TO 'recepcionista'@'localhost';
GRANT ALL ON clinica_veterinaria.mascota TO 'recepcionista'@'localhost';
GRANT ALL ON clinica_veterinaria.veterinario TO 'recepcionista'@'localhost';