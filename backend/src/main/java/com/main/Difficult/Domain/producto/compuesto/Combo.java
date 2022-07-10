package com.main.Difficult.Domain.producto.compuesto;

import com.fasterxml.jackson.annotation.JsonView;
import com.main.Difficult.Domain.producto.Lote;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Domain.producto.View;
import com.main.Difficult.Domain.producto.simple.ProductoSimple;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Document("Producto")
@TypeAlias("Combo")
public class Combo extends Producto {
    @JsonView(View.ProductoItem.class)
    private Set<ItemCombo> items = new HashSet<>();

    public Combo(String imagen, String nombre, String descripcion, Integer puntaje, String paisDeOrigen, Double precioBase) {
        super(imagen, nombre, descripcion, puntaje, paisDeOrigen, precioBase);
    }

    public void agregarProductoCombo(ProductoSimple item , Lote lote, Integer cantidad){
        items.add(new ItemCombo(item,lote,cantidad));
    }
    public void borrarProductoCombo(ProductoSimple item){
        items.removeIf((_item)-> _item.getProducto().getId() == item.getId());
    }
    @Override
    public Boolean hayProductoConMasDe4Meses(){
        return super.hayProductoConMasDe4Meses() || this.hayItemsConMasDe4Meses();
    }
    public Boolean hayItemsConMasDe4Meses(){
        return items.stream().anyMatch((item)-> item.tieneMasDe4Meses());
    }
    public Double precio(){
        AtomicReference<Double> itemsCalculoPrecio = new AtomicReference<>(0.0);
        items.forEach((item) -> itemsCalculoPrecio.updateAndGet(v -> v + item.precioBase()));
        return (0.85 * (itemsCalculoPrecio.get() + 20 * items.size())) *this.descuento();

    }


}
