package com.main.Difficult.Controllers;

import com.main.Difficult.Deserializers.ItemCarritoDes;
import com.main.Difficult.Domain.carrito.ItemCarrito;
import com.main.Difficult.Services.CarritoSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={"*"})
public class CarritoController {
    @Autowired private CarritoSerive carritoService;

    @PostMapping("/carrito/{uid}/agregar")
    public void agregarItem(@Validated @RequestBody ItemCarritoDes itemCarrito , @PathVariable Long uid){
        carritoService.agregarItem(uid,itemCarrito);
    }

    @DeleteMapping("/carrito/{uid}/quitar/{itemId}")
    public void quitarItem(@PathVariable Long uid, @PathVariable Long itemId){
        carritoService.quitarItem(uid,itemId);
    }

    @DeleteMapping("/carrito/{uid}/limpiar")
    public void limpiarCarrito(@PathVariable Long uid){
        carritoService.limpiarCarrito(uid);
    }

    @GetMapping("/carrito/{uid}/items")
    public List<ItemCarrito> items (@PathVariable Long uid){
        return carritoService.items(uid);
    }

    @PostMapping("/carrito/{uid}/comprar")
    public void finalizarCompra(@PathVariable Long uid){
        carritoService.finalizarCompra(uid);
    }
}
