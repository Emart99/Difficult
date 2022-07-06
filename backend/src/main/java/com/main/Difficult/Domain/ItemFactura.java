package com.main.Difficult.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Articulo")
public class ItemFactura {
    @Id @GeneratedValue
    @JsonIgnore public Long id =null;
    // producto y lote van aca
    private Integer cantidad = 0;
    private Double precioCompra = 0.00;

    public Integer getCantidad(){return this.cantidad;}
    public Double getPrecioCompra(){return this.precioCompra;}

    public void setCantidad(Integer _cantidad){this.cantidad = _cantidad;}
    public void setPrecioCompra(Double _precioCompra){this.precioCompra = _precioCompra;}

    public ItemFactura(Integer _cantidad){
        this.setCantidad(_cantidad);
    }
}
