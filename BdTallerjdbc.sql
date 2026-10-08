--Crear Tabla Clientes

drop table clientes;

create table clientes(
    cedula char(10) not null,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    edad INT not null,
    constraint clientes_pk primary key(cedula)
);



select * from clientes


select * from estudiantes


INSERT INTO clientes (cedula, nombre, apellido, edad) VALUES
('0167890123', 'Sofía',  'Hernández', 31)