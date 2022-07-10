package com.main.Difficult.Domain.producto.compuesto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.main.Difficult.Domain.producto.Lote;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.producto.View;
import com.main.Difficult.Domain.producto.simple.ProductoSimple;
import com.main.Difficult.Utils.Keys;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
@Getter @Setter
public class ItemCombo {
    @Id
    @JsonIgnore private String id;
    @JsonView(View.Item.class)
    private Producto producto;
    @JsonView(View.Item.class)
    private Integer cantidad = 0;
    @JsonView(View.Item.class)
    private Lote lote;
    public ItemCombo(ProductoSimple producto, Lote lote, Integer cantidad){
        id = producto.getId();
        this.setProducto(producto);
        this.setLote(lote);
        this.setCantidad(cantidad);
    }
    public ItemCombo(){}

    public Double precioBase(){
        return producto.getPrecioBase();
    }
    public Boolean tieneMasDe4Meses(){
        return lote.tieneMasDe4Meses();
    }
    @JsonProperty public String producto(){return producto.getNombre();}
    @JsonProperty("Precio") public String productoPrecio() {return "$ "+producto.getPrecioBase().toString();}
    @JsonProperty public String lote(){return lote.getId().toString();}
}
