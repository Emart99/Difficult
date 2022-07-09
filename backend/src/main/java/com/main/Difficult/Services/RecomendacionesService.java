package com.main.Difficult.Services;

import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Repositories.mongo.ProductoRepository;
import com.main.Difficult.Repositories.neo4j.FacturasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecomendacionesService {
    @Autowired private FacturasRepository repoFacturasGrafito;
    @Autowired private ProductoRepository repoProductos;


    @Transactional
    public List<Producto> recomendacionProductosByProductoId(String productoId, Long usuarioId){
       var listaIds = usuarioId != null ? repoFacturasGrafito.recomendacionProductosByProductoId(productoId, usuarioId):repoFacturasGrafito.recomendacionProductosByProductoIdSinUsuario(productoId);
            return (List<Producto>) repoProductos.findAllById(listaIds);

    }
}
