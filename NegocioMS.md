# Local de venta 

## Contenido


## Tecnologias a implementar

El sistema se va a implementar en Java 11 utilizando el Framework Spring para el desarrollo de los microservicios de procesamiento (también conocido como Backend). El frontend va a ser desarrollado en Javascript con el Framework React

## Microservicios comprendidos

- Admin service: Desplegado sobre la computadora central, conecta a todos los microservicios. Tiene capacidad de consultar stock, ordenar movimientos de productos
- Deposito service: conexion directa con la base de datos de productos. Aquí se van a registrar los productos disponibles y los ingresos de productos que se realicen (compras/restockeo)
- Products service: tiene acceso al listado de productos que el negocio maneja y los precios que les son asignados
- Caja: servicio liviano que se conecta con lector de código de barras, recupera precios y arma carritos de venta
- Billing service(facturación): Encargada de poner las cosas en orden para ser presentadas a un contador.
- Mobile App: aplicacion de celular que recibe datos estadisticos y permite consultar stock de productos




## Admin Service

Reune informacion de todos los servicios desplegados

## Products service

Gestiona el listado de productos y el historico de precios que ha tenido.

## Stock service

Este servicio se va a desplegar una instancia por cada depósito, cada una con nombres identificatorios. 

## Caja service (punto de venta)

Recoge los datos de precios asignados desde el 

## Billing service

Servicio que hace de soporte para la facturacion del negocio

