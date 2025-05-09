CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    second_last_name VARCHAR(50),
    employee_number VARCHAR(200) UNIQUE NOT NULL,
    email VARCHAR(100) DEFAULT NULL,
    phone_number VARCHAR(20) DEFAULT NULL,
    enabled BOOLEAN NOT NULL,
    creator_user VARCHAR(30) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modification_date TIMESTAMP,
    modification_user VARCHAR(30)
);

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(36) NOT NULL,
    password VARCHAR(100) NOT NULL,
    employee_id INT NOT NULL,
    user_role VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL,
    register_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    failed_login_attempts INT NOT NULL DEFAULT 0,
    lock_time TIMESTAMP NULL,
    UNIQUE KEY username_uk(username),
    CONSTRAINT fk_user_emp FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
