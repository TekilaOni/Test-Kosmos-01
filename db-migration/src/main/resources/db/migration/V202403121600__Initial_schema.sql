
CREATE TABLE speciality (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL,
    creator_user VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP,
    modification_user VARCHAR(30)
);

CREATE TABLE doctor (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    second_last_name VARCHAR(100),
    email VARCHAR(100) DEFAULT NULL,
    phone_number VARCHAR(20) DEFAULT NULL,
    speciality_id INT NOT NULL,
    enabled BOOLEAN NOT NULL,
    creator_user VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP,
    modification_user VARCHAR(30),
    FOREIGN KEY (speciality_id) REFERENCES speciality(id)
);

CREATE TABLE patient (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    second_last_name VARCHAR(50),
    email VARCHAR(100) DEFAULT NULL,
    phone_number VARCHAR(20) DEFAULT NULL,
    enabled BOOLEAN NOT NULL,
    creator_user VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP,
    modification_user VARCHAR(30)
);

CREATE TABLE medical_office (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    number INT NOT NULL,
    floor INT NOT NULL,
    enabled BOOLEAN NOT NULL,
    creator_user VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP,
    modification_user VARCHAR(30)
);

CREATE TABLE appointment (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT NOT NULL,
    patient_id INT NOT NULL,
    medical_office_id INT NOT NULL,
    appointment_date TIMESTAMP NOT NULL,
    duration INT NOT NULL DEFAULT 30,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    enabled BOOLEAN NOT NULL,
    creator_user VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP,
    modification_user VARCHAR(30),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (medical_office_id) REFERENCES medical_office(id)
);

INSERT INTO speciality (name, enabled, creator_user, creation_date) VALUES
('Cardiología', TRUE, 'admin', NOW()),
('Pediatría', TRUE, 'admin',NOW()),
('Neurología', TRUE, 'admin',NOW()),
('Ortopedia', TRUE, 'admin',NOW()),
('Dermatología', TRUE, 'admin',NOW());


INSERT INTO speciality (name, enabled, creator_user, creation_date) VALUES
('Cardiología', TRUE, 'admin', NOW()),
('Pediatría', TRUE, 'admin', NOW()),
('Neurología', TRUE, 'admin', NOW()),
('Ortopedia', TRUE, 'admin', NOW()),
('Dermatología', TRUE, 'admin', NOW());

INSERT INTO doctor (name, last_name, second_last_name, email, phone_number, speciality_id, enabled, creator_user, creation_date) VALUES
('Juan', 'Pérez', 'Gómez', 'juan.perez@hospital.com', '555-0101', 1, TRUE, 'admin', NOW()),
('María', 'López', 'Martínez', 'maria.lopez@hospital.com', '555-0102', 1, TRUE, 'admin', NOW()),
('Carlos', 'García', 'Rodríguez', 'carlos.garcia@hospital.com', '555-0103', 2, TRUE, 'admin', NOW()),
('Ana', 'Martínez', 'Sánchez', 'ana.martinez@hospital.com', '555-0104', 2, TRUE, 'admin', NOW()),
('Luis', 'Hernández', 'Ramírez', 'luis.hernandez@hospital.com', '555-0105', 3, TRUE, 'admin', NOW()),
('Sofía', 'González', 'Díaz', 'sofia.gonzalez@hospital.com', '555-0106', 3, TRUE, 'admin', NOW()),
('Miguel', 'Torres', 'Vega', 'miguel.torres@hospital.com', '555-0107', 4, TRUE, 'admin', NOW()),
('Laura', 'Ramírez', 'Cruz', 'laura.ramirez@hospital.com', '555-0108', 4, TRUE, 'admin', NOW()),
('Diego', 'Sánchez', 'Moreno', 'diego.sanchez@hospital.com', '555-0109', 5, TRUE, 'admin', NOW()),
('Elena', 'Vega', 'Rojas', 'elena.vega@hospital.com', '555-0110', 5, TRUE, 'admin', NOW());


INSERT INTO patient (name, last_name, second_last_name, email, phone_number, enabled, creator_user, creation_date) VALUES
('Lucía', 'Rodríguez', 'Gómez', 'lucia.rodriguez@correo.com', '555-0201', TRUE, 'admin', NOW()),
('Pedro', 'Sánchez', 'López', 'pedro.sanchez@correo.com', '555-0202', TRUE, 'admin', NOW()),
('Carmen', 'Gómez', 'Martínez', 'carmen.gomez@correo.com', '555-0203', TRUE, 'admin', NOW()),
('Javier', 'Díaz', 'Pérez', 'javier.diaz@correo.com', '555-0204', TRUE, 'admin', NOW()),
('Isabel', 'Moreno', 'Ramírez', 'isabel.moreno@correo.com', '555-0205', TRUE, 'admin', NOW());

INSERT INTO medical_office (number, floor, enabled, creator_user, creation_date) VALUES
(101, 1, TRUE, 'admin', NOW()),
(102, 1, TRUE, 'admin', NOW()),
(201, 2, TRUE, 'admin', NOW()),
(202, 2, TRUE, 'admin', NOW()),
(301, 3, TRUE, 'admin', NOW());