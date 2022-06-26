# DifficultApp Docker

## Contiene 7 contenedores:
- #### Kotlin (Spring Boot & Gradle (Backend))
- #### React (Frontend)
- #### MySql (Para usuarios y facturas)
- #### MongoDB (Para productos y lotes)
- #### Neo4j (Para sugerencias)
- #### Redis (Para el carrito)
- #### Redis-Commander (para visualizar datos de redis)

### Para ejecutarlo, desde el directorio del proyecto (donde se encuentra el docker-compose.yml) ejecutar:
#### `docker-compose up --build`


<br/>

#### También es posible ejecutar la base de datos de Mongo en forma de cluster; que cuenta con dos shards y sus respectivos replica sets y un arbitro en cada shard, un router, un config servers y su replica set. Para poder ejecutarlo de esta manera se debe descomentar del archivo docker compose los "Config Servers" y los "Shards".
#### Luego, ejecutar en una nueva terminal el siguiente comando:
#### `sh init.sh`

<br/>

**Nota:**
**Muy recomendable correrlo directo con docker debido a la gran cantidad de dependencias necesarias para correr el backend (4 base de datos)**

