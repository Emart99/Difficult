package com.main.Difficult.Domain.carrito;

import com.main.Difficult.Deserializers.ItemCarritoDes;
import com.main.Difficult.DifficultApplication;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.usuario.ItemFactura;
import com.main.Difficult.Utils.Keys;
import io.lettuce.core.KeyScanCursor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.stream.Collectors;

@RedisHash("ItemCarrito")
public class ItemCarrito {
    // mi amigo el singleton
    @Id public Long id = Keys.getInstance().getContador();
    @Getter @Setter private String productoId;
    @Getter @Setter private Long loteId;
    @Getter @Setter private Integer cantidad;
    @Getter @Setter private String imagen;
    @Getter @Setter private Double precio;
    @Getter @Setter private String nombre;
    @Getter @Setter private String descripcion;

    public ItemCarrito(){}
    public ItemCarrito(ItemCarritoDes item, Producto producto){
        this.loteId = item.loteId;
        this.productoId = producto.id;
        this.cantidad = item.cantidad;
        this.nombre = producto.getNombre();
        this.imagen = producto.getImagen();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.precio() * this.cantidad;
    }

    public ItemFactura itemFactura(List<Producto> lista){
        var producto = lista
                .stream()
                .filter((Producto _producto)->_producto.id.equals( this.productoId)).findFirst().get();
        var lote = producto.lotePorId(this.loteId);
        return new ItemFactura(producto,lote,this.cantidad);
    }

    public void updateCantidad(ItemCarrito item){
        this.cantidad += item.cantidad;
        this.precio += item.precio;
    }
}
