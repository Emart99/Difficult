package com.main.Difficult.Domain.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.Difficult.Domain.producto.Lote;
import com.main.Difficult.Domain.producto.Producto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Articulo")
public class ItemFactura {
    @Id @GeneratedValue
    @JsonIgnore public Long id =null;
    @JsonIgnore @Getter @Setter private Producto producto;
    @JsonIgnore @Getter @Setter private Lote lote;
    private Integer cantidad = 0;
    private Double precioCompra = 0.00;

    public Integer getCantidad(){return this.cantidad;}
    public Double getPrecioCompra(){return this.precioCompra;}

    public void setCantidad(Integer _cantidad){this.cantidad = _cantidad;}
    public void setPrecioCompra(Double _precioCompra){this.precioCompra = _precioCompra;}

    public ItemFactura(Producto producto, Lote lote, Integer cantidad){
        this.setCantidad(cantidad);
        this.setLote(lote);
        this.setProducto(producto);
        lote.descontar(cantidad);
        precioCompra = cantidad * producto.precio();
    }
}
