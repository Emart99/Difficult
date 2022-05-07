# DifficultApp Backend (grupo-5)


### Como ejecutarlo sin IDE
En un bash navegar hasta el path correspondiente al proyecto y luego ejecutar los siguientes comandos\
**./gradlew build** \
**./gradlew run**

## EndPoints disponibles

### Para el usuario

`POST /usuario/registrar`\
*Envia por body un usuario a registrar*\
**request**:
>{\
&ensp;"apellido": "asd"\
&ensp;"contrasenia": "asd123"\
&ensp;"edad": 23\
&ensp;"imagen": " "\
&ensp;"nombre": "jorge"\
&ensp;"saldo": 1\
&ensp;"usuarioNombre": "Zeferino"\
>}

`PUT /usuario/ingresar`\
*Recibe usuario y contraseña por body y devuelve el id del usuario si si matchea de lo contrario, un error*
**request**:
>{\
&ensp; "usuarioNombre": "Zeferino"\
&ensp; "contrasenia": "asd123"\
>}

`GET /usuario/perfil/{uid}`\
*Recibe el id del usuario por path y devuelve un usuario*\

`PUT /usuario/perfil/{uid}/editar`\
*Recibe un usuario nuevo , el id de un usuario existente y pisa al usuario existente con los datos del nuevo usuario*
**request**:
>{\
&ensp;"apellido": "Chávez"\
&ensp;"nombre": "Zeferinas"\
&ensp;"saldo": 2300000\
>}

### Para los productos

`GET /producto/{id}`\
*Trae un producto que matchee con el id enviado por path*\

`GET /producto/traer`\
*Recibe 3 parametros opcionales por query params para realizar un filtrado, en el caso de que todos sean null devuelve todos los productos*\
**Parametros posibles:**

    /producto/traer?puntaje=5&paisDeOrigen=""&nombre=""
    donde puntaje es un numero entre 0 y 5,
    paisDeOrigen String con un pais y
    nombre es una String con el nombre correspondiente
     



### Para el carrito
`POST /carrito/{uid}/agregar`\
*Recibe un id del usuario por path y un producto (con lote y cantidad) por body*

`DELETE /carrito/{uid}/quitar/{itemId}`\
*Elimina el producto del carrito con el id del usuario y el id del producto por path*

`DELETE /carrito/{uid}/limpiar`\
*Borra todo el carrito recibe un id de usuario por path*

`GET /carrito/{uid}/items`\
*Trae todos los items del carrito de un usuario recibe su id por path*

`POST /carrito/{uid}/comprar`\
*Ejecuta la compra del carrito del usuario recibe su id por path*

## DER
![](https://i.imgur.com/AZF5yKi.png)

## MYSQL DER
![](https://i.imgur.com/qPlrocx.png)