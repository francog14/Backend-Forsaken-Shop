-- Script SQL para crear todas las bases de datos necesarias
-- Backend-Forsaken-Shop Microservices

-- Crear bases de datos
CREATE DATABASE IF NOT EXISTS usuario_db;
CREATE DATABASE IF NOT EXISTS venta_db;
CREATE DATABASE IF NOT EXISTS detalle_venta_db;
CREATE DATABASE IF NOT EXISTS mensajeria_db;
CREATE DATABASE IF NOT EXISTS categoria_db;
CREATE DATABASE IF NOT EXISTS prenda_db;
CREATE DATABASE IF NOT EXISTS rol_db;

-- Usar las bases de datos

-- ========== USUARIO DB ==========
USE usuario_db;

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    run VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    id_rol INT NOT NULL
);

-- ========== VENTA DB ==========
USE venta_db;

CREATE TABLE IF NOT EXISTS ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    id_usuario INT NOT NULL
);

-- ========== DETALLE VENTA DB ==========
USE detalle_venta_db;

CREATE TABLE IF NOT EXISTS detalle_ventas (
    id_detalle_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_prenda INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL
);

-- ========== MENSAJERIA DB ==========
USE mensajeria_db;

CREATE TABLE IF NOT EXISTS mensajes (
    id_mensaje INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    asunto VARCHAR(255) NOT NULL,
    contenido LONGTEXT NOT NULL,
    fecha_envio DATETIME NOT NULL
);

-- ========== ROL DB ==========
USE rol_db;

CREATE TABLE IF NOT EXISTS roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(100) NOT NULL UNIQUE
);

-- Insertar datos de ejemplo en rol_db
INSERT INTO roles (nombre_rol) VALUES 
('Admin'),
('Usuario'),
('Vendedor'),
('Cliente');

-- ========== CATEGORIA DB ==========
USE categoria_db;

CREATE TABLE IF NOT EXISTS categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL UNIQUE
);

-- Insertar datos de ejemplo en categoria_db
INSERT INTO categorias (nombre_categoria) VALUES 
('Camisetas'),
('Pantalones'),
('Zapatos'),
('Accesorios'),
('Chaquetas');

-- ========== PRENDA DB ==========
USE prenda_db;

CREATE TABLE IF NOT EXISTS prendas (
    id_prenda INT AUTO_INCREMENT PRIMARY KEY,
    nombre_prenda VARCHAR(100) NOT NULL,
    precio_prenda INT NOT NULL,
    talla VARCHAR(20) NOT NULL,
    color VARCHAR(50) NOT NULL,
    stock_prenda INT NOT NULL,
    id_categoria INT NOT NULL
);

-- Insertar datos de ejemplo en prenda_db
INSERT INTO prendas (nombre_prenda, precio_prenda, talla, color, stock_prenda, id_categoria) VALUES 
('Camiseta Básica', 15000, 'M', 'Blanco', 50, 1),
('Camiseta Deportiva', 25000, 'L', 'Negro', 35, 1),
('Pantalón Casual', 45000, 'M', 'Azul', 40, 2),
('Pantalón Formal', 60000, 'L', 'Negro', 25, 2),
('Zapatillas Nike', 80000, '42', 'Rojo', 20, 3),
('Gorra', 12000, 'Única', 'Negro', 100, 4),
('Chaqueta Invierno', 120000, 'M', 'Gris', 15, 5);

-- Fin del script
