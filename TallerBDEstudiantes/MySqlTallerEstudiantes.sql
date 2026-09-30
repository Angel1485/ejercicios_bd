-- Creamos la base o esquema 

CREATE DATABASE taller_estudiantes;

-- Cuando se crea una bd con mysql en otro motor que no sea workbench se debe colocarla palabra USE para usar la base de datos de lo contrario no se podra utilizar
USE taller_estudiantes;

-- PARTE 1 y 2 -- Creamos la tabla con primary key y sus campos not null

CREATE TABLE estudiantes (
    id_estudiante INT PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    curso VARCHAR(50) NOT NULL,
    fecha_registro VARCHAR(10) NOT NULL
);

-- PARTE 3 --Ingreso de Registros

INSERT INTO estudiantes VALUES (1, 'Pedro', 'Alvarado', 21, 'Matematicas', '2026-01-05');
INSERT INTO estudiantes VALUES (2, 'Ana', 'Bermeo', 24, 'Fisica', '2026-01-12');
INSERT INTO estudiantes VALUES (3, 'Luis', 'Cabrera', 19, 'Quimica', '2026-01-20');
INSERT INTO estudiantes VALUES (4, 'Sofia', 'Duarte', 27, 'Matematicas', '2026-02-02');
INSERT INTO estudiantes VALUES (5, 'Diego', 'Espinoza', 22, 'Programacion', '2026-02-14');
INSERT INTO estudiantes VALUES (6, 'Valeria', 'Fuentes', 20, 'Fisica', '2026-02-25');
INSERT INTO estudiantes VALUES (7, 'Andres', 'Guaman', 23, 'Quimica', '2026-03-03');
INSERT INTO estudiantes VALUES (8, 'Camila', 'Hidalgo', 18, 'Programacion', '2026-03-11');
INSERT INTO estudiantes VALUES (9, 'Mateo', 'Iza', 26, 'Matematicas', '2026-03-22');
INSERT INTO estudiantes VALUES (10, 'Lucia', 'Jaramillo', 21, 'Fisica', '2026-04-04');
INSERT INTO estudiantes VALUES (11, 'Pedro', 'Alvarado', 21, 'Programacion', '2026-04-15');
INSERT INTO estudiantes VALUES (12, 'Ana', 'Bermeo', 24, 'Quimica', '2026-04-26');
INSERT INTO estudiantes VALUES (13, 'Carlos', 'Loor', 29, 'Matematicas', '2026-05-02');
INSERT INTO estudiantes VALUES (14, 'Elena', 'Mera', 17, 'Fisica', '2026-05-09');
INSERT INTO estudiantes VALUES (15, 'Jorge', 'Naranjo', 25, 'Programacion', '2026-05-18');

-- Paso 4 — Consultas SELECT

-- 1. Mostrar todos los registros
SELECT * FROM estudiantes;

-- 2. Mostrar únicamente nombres y curso
SELECT nombres, curso 
FROM estudiantes;

-- 3. Mostrar estudiantes mayores de 18 años
SELECT * 
FROM estudiantes 
WHERE edad > 18;

-- 4. Mostrar estudiantes entre 18 y 25 años
SELECT * 
FROM estudiantes 
WHERE edad BETWEEN 18 AND 25;

-- 5. Mostrar estudiantes del curso "Base de Datos"
SELECT * 
FROM estudiantes 
WHERE curso = 'Base de Datos';

-- 6. Mostrar estudiantes registrados después de 2026-03-01
SELECT * 
FROM estudiantes 
WHERE fecha_registro > '2026-03-01';

-- 7. Mostrar estudiantes registrados entre 2026-01-01 y 2026-04-30
SELECT * 
FROM estudiantes 
WHERE fecha_registro BETWEEN '2026-01-01' AND '2026-04-30';

-- Paso 5 — UPDATE — Minimo 5

-- 1. Cambiar curso
UPDATE estudiantes SET curso = 'Inteligencia Artificial' WHERE id_estudiante = 3;

-- 2. Cambiar edad
UPDATE estudiantes SET edad = 28 WHERE id_estudiante = 9;

-- 3. Cambiar fecha
UPDATE estudiantes SET fecha_registro = '2026-06-10' WHERE id_estudiante = 13;

-- 4. Cambiar varios campos al mismo tiempo
UPDATE estudiantes SET nombres = 'Pedro Antonio', apellidos = 'Alvarado Ruiz' WHERE id_estudiante = 11;

-- 5. Cambiar curso y edad a la vez
UPDATE estudiantes SET curso = 'Fisica', edad = 30 WHERE id_estudiante = 15;

-- Paso 6 — DELETE — Minimo 5

-- 1. Eliminar por ID
DELETE FROM estudiantes WHERE id_estudiante = 15;

-- 2. Eliminar por curso
DELETE FROM estudiantes WHERE curso = 'Quimica';

-- 3. Eliminar por edad
DELETE FROM estudiantes WHERE edad = 17;

-- 4. Eliminar por nombre
DELETE FROM estudiantes WHERE nombres = 'Jorge';

-- 5. Eliminar por rango de fechas
DELETE FROM estudiantes WHERE fecha_registro < '2026-01-12';

-- Paso 7 — Agregar columna correo

ALTER TABLE estudiantes ADD COLUMN correo VARCHAR(100);

-- Paso 8 — Actualizar scripts con la nueva columna

-- Nuevos insert con correo

INSERT INTO estudiantes VALUES (16, 'Laura',  'Paredes', 21, 'Programacion', '2026-05-20', 'laura@gmail.com');
INSERT INTO estudiantes VALUES (17, 'Jorge',  'Nunez',   24, 'Base de Datos','2026-05-22', 'jorge@gmail.com');
INSERT INTO estudiantes VALUES (18, 'Elena',  'Cordero', 22, 'Redes',        '2026-05-25', 'elena@gmail.com');

-- UPDATE con correo

UPDATE estudiantes SET correo = 'pedro.alvarado@gmail.com' WHERE id_estudiante = 1;
UPDATE estudiantes SET correo = 'ana.bermeo@gmail.com' WHERE id_estudiante = 2;
UPDATE estudiantes SET correo = 'luis.cabrera@gmail.com' WHERE id_estudiante = 3;

-- SELECT con correo

SELECT nombres, apellidos, correo FROM estudiantes;

SELECT * FROM estudiantes WHERE correo LIKE '%gmail%';  --- Busca una palabara especifica dentro de una columna y devuelve todos los valores

-- Paso 9 — Consultas con fechas

-- 1. Registrados después de 2026-02-01
SELECT * FROM estudiantes WHERE fecha_registro > '2026-02-01';

-- 2. Registrados antes de 2026-05-01
SELECT * FROM estudiantes WHERE fecha_registro < '2026-05-01';

-- 3. Registrados entre dos fechas
SELECT * FROM estudiantes WHERE fecha_registro BETWEEN '2026-02-01' AND '2026-04-30';

-- 4. Registrados exactamente en 2026-03-15
SELECT * FROM estudiantes WHERE fecha_registro = '2026-03-15';

-- 5. Curso "Programacion" registrados después de 2026-01-01
SELECT * FROM estudiantes WHERE curso = 'Programacion' AND fecha_registro > '2026-01-01';