CREATE DATABASE barcode_stock_system;
USE barcode_stock_system;

-- Kullanıcılar tablosu
CREATE TABLE users(
	userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password_hash)
VALUES ('didem', 'didem123'),
       ('depo', 'depo123');


    


