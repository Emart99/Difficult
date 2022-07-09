package com.main.Difficult.Domain.producto.compuesto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.main.Difficult.Domain.producto.Lote;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.producto.View;
import com.main.Difficult.Domain.producto.simple.ProductoSimple;
import lombok.Getter;
import lombok.Setter;

public class ItemCombo {
    @JsonIgnore public String id;
    @JsonView(View.Item.class)
    @Getter @Setter private Producto producto;
    @JsonView(View.Item.class)
    @Getter @Setter private Integer cantidad = 0;
    @JsonView(View.Item.class)
    @Getter @Setter private Lote lote;
    // cambiar producto por ProductoSimple
    public ItemCombo(ProductoSimple _producto, Lote _lote, Integer _cantidad){
        this.setProducto(_producto);
        this.setLote(_lote);
        this.setCantidad(_cantidad);
    }

    public Double precioBase(){
        return producto.getPrecioBase();
    }
    public Boolean tieneMasDe4Meses(){
        return lote.tieneMasDe4Meses();
    }
    @JsonProperty public String producto(){return producto.getNombre();}
    @JsonProperty("Precio") public String productoPrecio() {return "$ "+producto.getPrecioBase().toString();}
    @JsonProperty public String lote(){return lote.id.toString();}
}
