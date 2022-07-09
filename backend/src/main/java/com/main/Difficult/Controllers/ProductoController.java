package com.main.Difficult.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.producto.View;
import com.main.Difficult.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ={"*"})
public class ProductoController {
    @Autowired private ProductoService productoService;

    @JsonView(View.DetalleDeProducto.class)
    @GetMapping("/producto/{idProducto}")
    public Producto findById(@PathVariable String idProducto){
        return productoService.findById(idProducto);
    }

    @JsonView(View.ProductoLista.class)
    @GetMapping("/producto/traer")
    public List<Producto> buscarPor(
            @RequestParam(value = "nombre" , required = false) String nombre,
            @RequestParam(value = "puntaje", required = false,defaultValue = "0") Integer puntaje,
            @RequestParam(value = "paisDeOrigen" ,required = false,defaultValue = "") String paisDeOrigen
    )
    {
        return productoService.buscarPor(nombre,paisDeOrigen,puntaje);
    }
}
