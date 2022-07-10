package com.main.Difficult.Repositories.mongo;

import com.main.Difficult.Domain.producto.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductoRepository extends MongoRepository<Producto,String> {

    @Query(" { $and:[{nombre: {'$regex':?0, '$options': 'i' }}, {paisDeOrigen: {'$regex':?1} }, {puntaje: {'$gte':?2}}] }")
    List<Producto> buscarPor(String nombre, String pais, Integer puntaje);
}
