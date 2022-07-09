package com.main.Difficult.Repositories.mongo;

import com.main.Difficult.Deserializers.ProductoDTO;
import com.main.Difficult.Domain.ClickLog;
import com.main.Difficult.Domain.producto.Producto;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ClickLogRepository extends MongoRepository<ClickLog,String> {
    @Aggregation(pipeline = { "{ $match:  {idUsuario: ?0}}",
            "{ $group:  {_id:$idProducto," +
                    "idUsuario:{$first:$idUsuario}," +
                    "cantidad:{$sum:1}," +
                    "idProducto: {$first: $idProducto}," +
                    "productoImagen:{$first: $productoImagen}," +
                    "productoNombre:{$first: $productoNombre}," +
                    "productoDescripcion:{$first: $productoDescripcion}," +
                    "productoValoracion:{$first: $productoValoracion} } }",
            "{ $sort: {cantidad:-1} }",
            "{ $limit: 1 }"})
    Optional<ClickLog> nombre(Long idUsuario);

    @Aggregation(pipeline = {
            "{$group:" +
                    "{_id:$idProducto," +
                    "cantidad:{$sum:1}," +
                    "idProducto:{$first:$idProducto}," +
                    "productoImagen:{$first:$productoImagen}," +
                    "productoNombre:{$first:$productoNombre}," +
                    "productoDescripcion:{$first: $productoDescripcion} } }," ,
            "{$sort:{cantidad:-1}}," ,
            "{$limit:4}"})
    List<ProductoDTO> productosMasVistos();
}
