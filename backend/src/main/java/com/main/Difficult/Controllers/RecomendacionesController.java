package com.main.Difficult.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.producto.View;
import com.main.Difficult.Services.RecomendacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class RecomendacionesController {
    @Autowired private RecomendacionesService recomendacionesService;

    @JsonView(View.ProductoRecomendado.class)
    @GetMapping(value = {"/producto/{pid}/usuario/{uid}", "/producto/{pid}/usuario"})
    public List<Producto> recomendacionProductos(@PathVariable String pid, @PathVariable(required = false) Long uid){
        return recomendacionesService.recomendacionProductosByProductoId(pid,uid);
    }
}
