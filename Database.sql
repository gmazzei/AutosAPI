--DROP TABLE auto_opcional;
--DROP TABLE autos;
--DROP TABLE tipos;
--DROP TABLE opcionales;


CREATE TABLE autos (
	id INT NOT NULL AUTO_INCREMENT, 
	tipo_id INT NOT NULL,
	PRIMARY KEY (id) 
);

CREATE TABLE tipos (
	id INT NOT NULL AUTO_INCREMENT, 
	nombre VARCHAR(200) NOT NULL, 
	precio DOUBLE NOT NULL, 
	PRIMARY KEY (id) 
);


CREATE TABLE opcionales (
	id INT NOT NULL AUTO_INCREMENT, 
	nombre VARCHAR(200) NOT NULL UNIQUE,
	sigla VARCHAR(10) NOT NULL UNIQUE,
	precio DOUBLE NOT NULL, 
	PRIMARY KEY (id) 
);

CREATE TABLE auto_opcional (
	auto_id INT NOT NULL, 
	opcional_id INT NOT NULL, 
	PRIMARY KEY (auto_id, opcional_id) 
);


INSERT INTO tipos
(nombre, precio)
VALUES
('Familiar', 245000.0),
('Coupe', 270000.0),
('Sedan', 230000.0);


INSERT INTO opcionales 
(nombre, sigla, precio)
VALUES
('Airbag', 'AR', 7000.0),
('Aire Acondicionado', 'AA', 20000.0),
('Llantas de Aleacion', 'LL', 12000.0),
('Sistema de Frenos ABS', 'ABS', 14000.0),
('Techo Corredizo', 'TC', 12000.0);
