package com.main.Difficult.Services;

import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Exceptions.NotFoundException;
import com.main.Difficult.Repositories.mongo.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {
    @Autowired private ProductoRepository repoProductos;

    @Transactional(readOnly = true)
    public Producto findById(String idProducto){
        return repoProductos
                .findById(idProducto)
                .orElseThrow(()-> new NotFoundException("Producto Inexistente"));
    }
    @Transactional(readOnly = true)
    public List<Producto> buscarPor(String nombre, String pais, Integer puntaje){
        return repoProductos.buscarPor(nombre,pais,puntaje);
    }
}
