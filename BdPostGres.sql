--Crear Tabla Clientes

drop table clientes; --Eliminar tabla

create table clientes(
    cedula char(10) not null,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    edad INT not null,
    constraint clientes_pk primary key(cedula)
);

--Insertar datos

INSERT INTO public.clientes (cedula, nombre, apellido, edad) VALUES('0105', 'Angel', 'Sarango', 36);
INSERT INTO public.clientes (cedula, nombre, apellido, edad) VALUES('0106', 'Angel', 'Sarango', 35);
INSERT INTO public.clientes (cedula, nombre, apellido, edad) VALUES('0107', 'Angel', 'Sarango', 37);
INSERT INTO public.clientes (cedula, nombre, apellido, edad) VALUES('0108', 'Angel', 'Sarango', 38);
INSERT INTO public.clientes (cedula, nombre, apellido, edad) VALUES('0109', 'Angel', 'Sarango', 39);
INSERT INTO public.clientes (cedula, nombre, apellido, edad) VALUES('0110', 'Angel', 'Sarango', 40);
INSERT INTO public.clientes (cedula, nombre, apellido, edad) VALUES('0111', 'Angel', 'Sarango', 40);


--Mostrar datos BD

--Todos
select * 
from clientes c 

--Campos seleccionados
select nombre, edad  
from clientes 

--Filtros de BD
select * 
from clientes c 
where c.edad > 35

select * 
from clientes c 
where c.edad between 37 and 40

select * 
from clientes c 
where c.edad > 37 and c.edad < 40

select * 
from clientes c 
where c.edad = 37 or c.edad = 40

---Actulizando datos

UPDATE public.clientes SET nombre = 'María', apellido = 'González' WHERE cedula = '0106';
UPDATE public.clientes SET nombre = 'Carlos', apellido = 'Ramírez' WHERE cedula = '0107';
UPDATE public.clientes SET nombre = 'Lucía', apellido = 'Fernández' WHERE cedula = '0108';
UPDATE public.clientes SET nombre = 'Andrés', apellido = 'Morales' WHERE cedula = '0109';
UPDATE public.clientes SET nombre = 'Valeria', apellido = 'Chávez' WHERE cedula = '0110';
UPDATE public.clientes SET nombre = 'Diego', apellido = 'Torres' WHERE cedula = '0111';

--Eliminar

DELETE FROM public.clientes 
WHERE cedula='111';




