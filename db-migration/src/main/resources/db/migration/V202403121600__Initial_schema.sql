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
    enabled BOOLEAN NOT NULL,
    creator_user VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP,
    modification_user VARCHAR(30),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (medical_office_id) REFERENCES medical_office(id)
);
