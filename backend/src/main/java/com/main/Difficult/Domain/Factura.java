package com.main.Difficult.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Node("Factura")
public class Factura {
    @org.springframework.data.neo4j.core.schema.Id @org.springframework.data.neo4j.core.schema.GeneratedValue
    @Id @GeneratedValue
    public Long ordenDeComptra = null;
    @Getter @Setter private Integer cantidadDeArticulos = 0;
    @Getter @Setter private Double importeTotal = 0.00;
    @Relationship(type = "COMPRA",direction = Relationship.Direction.INCOMING)
    @Transient @JsonIgnore
    @Getter @Setter private Usuario user = new Usuario();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Getter @Setter private LocalDateTime fechaDeCompra = LocalDateTime.now();

    @Relationship(type = "ITEMS")
    @JsonIgnore
    @Transient
    @Getter @Setter private List<ItemFactura> items = new ArrayList<>();
    private Integer sumOfItemListCantidad(List<ItemFactura> _items){
        Integer sum = 0;
        for (ItemFactura item : _items) {
           sum += item.getCantidad();
        }
        return sum;
    }
    private Double sumOfItemListPrecioCompra(List<ItemFactura> _items){
        double precio = 0;
        for (ItemFactura item : _items) {
            precio += item.getPrecioCompra();
        }
        return precio;
    }

    public Factura(List<ItemFactura> _items, Usuario _user){
        this.setItems(_items);
        this.setUser(_user);
        this.setCantidadDeArticulos(sumOfItemListCantidad(_items));
        this.setImporteTotal(sumOfItemListPrecioCompra(_items));
    }
}



