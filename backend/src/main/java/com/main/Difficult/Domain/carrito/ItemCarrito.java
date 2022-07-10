package com.main.Difficult.Domain.carrito;

import com.main.Difficult.Deserializers.ItemCarritoDes;
import com.main.Difficult.DifficultApplication;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.usuario.ItemFactura;
import com.main.Difficult.Exceptions.NotFoundException;
import com.main.Difficult.Utils.Keys;
import io.lettuce.core.KeyScanCursor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@RedisHash("ItemCarrito")
public class ItemCarrito {
    // mi amigo el singleton
    @Id private Long id = Keys.getInstance().getContador();
    private String productoId;
    private Long loteId;
    private Integer cantidad;
    private String imagen;
    private Double precio;
    private String nombre;
    private String descripcion;

    public ItemCarrito(){}
    public ItemCarrito(ItemCarritoDes item, Producto producto){
        this.loteId = item.loteId;
        this.productoId = producto.getId();
        this.cantidad = item.cantidad;
        this.nombre = producto.getNombre();
        this.imagen = producto.getImagen();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.precio() * this.cantidad;
    }

    public ItemFactura itemFactura(List<Producto> lista){
        var producto = lista
                .stream()
                .filter(_producto->_producto.getId().equals(this.productoId)).findFirst().orElseThrow();
        var lote = producto.lotePorId(this.loteId);
        return new ItemFactura(producto,lote,this.cantidad);
    }

    public void updateCantidad(ItemCarrito item){
        this.cantidad += item.cantidad;
        this.precio += item.precio;
    }
}
