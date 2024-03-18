# Practica de productos

Leer el fichero del directorio "data" llamado products.csv.

Procesar cada una las líneas en una lista de Productos.

Un producto tiene
- Int id
- String name;
- Int supplier;
- Int category;
- Double unitPrice;
- Int unitsInStock;

Realizar las siguientes consultas sobre dicha colección:
- Todos los productos: Equivale a select * from products
- Nombre de los productos: select name from products
- Nombre de los productos cuyo stock es menor que 10: select name from products where units_in_stock < 10
- Nombre de los productos donde el stock sea menor a 5 ordenador por unidades de stock ascendente: select name from products where units_in_stock < 5 order by units_in_stock asc
- Saca el número de proveedores existentes: select count(1), supplierID from products GROUP BY  supplierID
- Obtener por cada producto, el numero de existencias.
- Por cada proveedor, obtener el número de productos
- Por cada proveedor obtener la media del precio.
- Obtener el producto más caro.
- Obtener los proveedores que tenga mas de 5 productos.
- Obtener los proveedores cuya suma de precios supere 100.
- Categorías y número de productos por categoría.
- Categoría más cara.
- Precio máximo, mínimo medio y cantidad por categoría.