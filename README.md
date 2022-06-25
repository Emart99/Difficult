# DifficultApp Docker (grupo-5)

## Contiene 4 contenedores:
- #### Kotlin (Spring Boot & Gradle)
- #### React
- #### MySql (Para usuarios y facturas)
- #### MongoDB (Para productos y lotes)
- #### Adminer (herramienta para administrar MYSQL)

### Para ejecutarlo, desde el directorio del proyecto (donde se encuentra el docker-compose.yml) ejecutar:
#### `docker-compose up --build`
### Luego de que se creen los contenedores, ejecutar en una nueva terminal:
#### `sh init.sh`

**Nota:**
**Tanto el backend como el frontend pueden ejecutarse fuera de docker siguiendo las intrucciones que hay en sus respectivos 'readme'**
