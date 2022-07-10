package com.main.Difficult.Services;

import com.main.Difficult.Deserializers.ItemCarritoDes;
import com.main.Difficult.Domain.carrito.Carrito;
import com.main.Difficult.Domain.carrito.ItemCarrito;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.usuario.Factura;
import com.main.Difficult.Domain.usuario.ItemFactura;
import com.main.Difficult.Repositories.mongo.ProductoRepository;
import com.main.Difficult.Repositories.mysql.UsuarioRepository;
import com.main.Difficult.Repositories.neo4j.FacturasRepository;
import com.main.Difficult.Repositories.redis.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CarritoSerive {
    @Autowired
    private CarritoRepository repoCarrito;
    @Autowired
    private UsuarioRepository repoUsuario;
    @Autowired
    private ProductoRepository repoProducto;
    @Autowired
    private FacturasRepository repoFactura;


    @Transactional
    public void agregarItem(Long uid, ItemCarritoDes itemCarrito){
        var carrito = repoCarrito.findById(uid).orElse(new Carrito(uid));
        var producto = repoProducto.findById(itemCarrito.productoId).get();
        var itemAActualizar = carrito.items
                .stream()
                .filter(item-> item.getLoteId().equals(itemCarrito.loteId) && item.getProductoId().equals(itemCarrito.productoId))
                .findFirst();
        if(itemAActualizar.isPresent()){
            carrito.items.remove(itemAActualizar.get());
            itemAActualizar.get().updateCantidad(new ItemCarrito(itemCarrito,producto));
            carrito.items.add(itemAActualizar.get());
        }
        else{
            carrito.items.add(new ItemCarrito(itemCarrito,producto));
        }
        repoCarrito.save(carrito);
    }

    @Transactional
    public void quitarItem(Long uid, Long itemId){
        var carrito = repoCarrito.findById(uid).get();
        var itemABorrar = carrito.items
                .stream()
                .filter((item)->item.getId() == itemId)
                .findFirst()
                .get();
       carrito.items.remove(itemABorrar);
       repoCarrito.save(carrito);
    }

    @Transactional
    public void limpiarCarrito(Long uid){
        repoCarrito.deleteById(uid);
    }

    @Transactional(readOnly = true)
    public List<ItemCarrito> items(Long uid){
        return repoCarrito.findById(uid).orElse(new Carrito(uid)).items;
    }

    @Transactional
    public void finalizarCompra(Long uid){
        try{
            var usuario = repoUsuario.findById(uid).get();
            var facturas = repoUsuario.findAllFacturasByUserId(uid);
            usuario.setFacturas(facturas);
            var carrito = repoCarrito.findById(uid).get();
            var factura = new Factura(crearItemsFacturas(carrito.items),usuario);
            usuario.comprar(factura);
            repoUsuario.save(usuario);
            repoCarrito.delete(carrito);
            repoFactura.save(factura);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private List<ItemFactura> crearItemsFacturas(List<ItemCarrito> itemCarritos){
        System.out.println(this.itemToIds(itemCarritos));
        var productos = repoProducto.findAllById(this.itemToIds(itemCarritos));;
        System.out.println(productos);
        var itemsFactura =  itemCarritos
                .stream()
                .map(item->item.itemFactura((List<Producto>) productos)).collect(Collectors.toList());
        System.out.println(itemsFactura);
        repoProducto.saveAll(productos);
        return  itemsFactura;
    }

    private List<String> itemToIds(List<ItemCarrito> items){
        var ids = items.stream().map(item -> item.getProductoId()).collect(Collectors.toList());
        return ids;
    }

}
