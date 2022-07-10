package com.main.Difficult.Domain.usuario;

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
@Getter @Setter
@Entity @Node("Factura")
public class Factura {
    @org.springframework.data.neo4j.core.schema.Id @org.springframework.data.neo4j.core.schema.GeneratedValue
    @Id @GeneratedValue
    private Long ordenDeComptra = null;
    private Integer cantidadDeArticulos = 0;
    private Double importeTotal = 0.00;
    @Relationship(type = "COMPRA",direction = Relationship.Direction.INCOMING)
    @Transient @JsonIgnore
    private Usuario user = new Usuario();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime fechaDeCompra = LocalDateTime.now();

    @Relationship(type = "ITEMS")
    @JsonIgnore
    @Transient
    private List<ItemFactura> items = new ArrayList<>();
    private Integer sumOfItemListCantidad(List<ItemFactura> _items){
        var sum = _items.stream().mapToInt(item -> item.getCantidad()).sum();
        return sum;
    }
    private Double sumOfItemListPrecioCompra(List<ItemFactura> _items){
        var precio =_items.stream().mapToDouble(item->item.getPrecioCompra()).sum();
        return precio;

    }

    public Factura(List<ItemFactura> _items, Usuario _user){
        this.setItems(_items);
        this.setUser(_user);
        cantidadDeArticulos = sumOfItemListCantidad(_items);
        importeTotal = sumOfItemListPrecioCompra(_items);
    }
    public Factura(){}
}



