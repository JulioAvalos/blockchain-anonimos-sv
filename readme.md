# ⛓ Blockchain Anonimos Server SV ⛏💎

### Instrucciones para correr el proyecto:

```
mvn clean
mvn install
./mvnw spring-boot: run
```
Agregar el archivo application-prod.yml en src/main/resources, para conectarse a la base de datos:
```
server:
  port: 8081

spring:
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://${host}:${port}/${database}
    username: ${username}
    password: ${password}
  jpa:
    database: mysql
    hibernate.ddl-auto: update
    generate-ddl: false
    show-sql: true
  sql:
    init:
      mode: never
```

En el archivo application.yml, se configura cual perfil o profile leer para conectarse a la base en memoria h2 o a la de produccion:

```
spring:
  profiles:
    active: dev
```
La aplicacion corre en el siguiente url: `http://localhost:8080/`

## Requerimientos basico de blockchain: 

1. - [x] Minar en la blockchain -> generar bloques y recibir criptomonedas en billetera ️
2. - [x] Hacer transacciones entre billeteras (enviar de una billetera a otra y poner la cantidad)️
3. - [x] Consultar todos los bloques minados 
4. - [ ] Consultar todas las transacciones hechas entre billetera y montos 
5. - [ ] Crear billeteras 
6. - [ ] Consultar billeteras 
7. - [ ] Login del server 

## Admin:
1. - [ ] Consultar billeteras y montos 
2. - [ ] Consultar usuarios de la plataforma y sus billeteras 
