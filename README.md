# Desafio Técnico Coopeuch - Back desarrollado en Java Springboot

## Descripción
El proyecto se desarrollo para postular al cargo de desarrollador full stack en Coopech.

## Como instalar y correr el proyecto

### Base de Datos

El proyecto usa base de datos SQL Server 2017. Se debe crear una base de datos llamada "coopeuch" y luego puede crear la tabla con el siguiente script o dejar que JPA lo haga por si mismo:
```
CREATE TABLE dbo.tareas (
    id_tarea int NOT NULL,
    es_vigente bit NULL,
    fecha_creacion datetime2(7) NULL,
    descripcion] varchar(250) NULL,
    PRIMARY KEY 
    ([id_tarea] ASC)
) 
```

### Proyecto Java
Una vez descargado el proyecto, abrir la linea de comando CMD (Windows) y navegar a la raiz del proyecto. Una vez hay, ejecutar el siguiente comando que compilara y ejecutara el proyecto back:
```
./gradlew bootRun
```

## Como correr pruebas unitarias

Al igual que en el paso anterior, ir a la raiz del proyecto y ejecutar la siguiente línea de comanado:
```
./gradlew test
```

## Documentacion con Swagger

Cuando proyecto este corriendo, abrir la siguiente URL:
http://localhost:8080/swagger-ui/index.html