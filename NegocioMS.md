# Local de venta 

## Contenido


## Tecnologias a implementar

El sistema se va a implementar en Java 11 utilizando el Framework Spring para el desarrollo de los microservicios de procesamiento (también conocido como Backend). El frontend va a ser desarrollado en Javascript con el Framework React

## Microservicios comprendidos

- Admin server: Desplegado sobre la computadora central, conecta a todos los microservicios. Tiene capacidad de consultar stock, ordenar movimientos de productos
- Stock service: conexion directa con la base de datos de productos. Aquí se van a registrar los productos disponibles y los ingresos de productos que se realicen (compras/restockeo)
- Caja: servicio liviano que se conecta con lector de código de barras, recupera precios y arma carritos de venta
- Billing service(facturación): Encargada de poner las cosas en orden para ser presentadas a un contador.
- Gestor de Depósito: también mantiene un stock de productos pero unicamente de los productos que tiene el deposito en cuestion, se puede conectar al stock service central para conocer los productos que tienen faltantes. Es posible activar alarmas para productos de mucha venta.
- App: aplicacion de celular que recibe datos estadisticos y permite consultar stock de productos




## Admin Service

## Stock service

Este servicio se va a desplegar UNA instancia por cada depósito, cada una con nombres identificatorios


## Caja service

## Billing




##### A posteriori 

- Critical data service: servicio de estadisticas avanzadas