# DifficultApp Docker

## Contiene 4 contenedores:
- #### Kotlin (Spring Boot & Gradle)
- #### React
- #### MySql (Para usuarios y facturas)
- #### MongoDB (Para productos y lotes)
- #### Neo4j (Para sugerencias)
- #### Redis (Para el carrito)

### Para ejecutarlo, desde el directorio del proyecto (donde se encuentra el docker-compose.yml) ejecutar:
#### `docker-compose up --build`
### Luego de que se creen los contenedores, ejecutar en una nueva terminal:
#### `sh init.sh`

**Nota:**
**Muy recomendable correrlo directo con docker debido a la gran cantidad de dependencias necesarias para correr el backend (4 base de datos)**

