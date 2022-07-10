package com.main.Difficult.Domain.producto.simple;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter @Setter
@Document("Producto")
@TypeAlias("Pintura")
public class Pintura extends ProductoSimple{
    private Double volumen = 0.00;
    private String color = "#008000";
    private Double rendimiento = 0.0;

    public Pintura(String imagen, String nombre, String descripcion, Integer puntaje, String paisDeOrigen, Double precioBase,Double volumen, String color, Double rendimiento) {
        super(imagen, nombre, descripcion, puntaje, paisDeOrigen, precioBase);
        this.setVolumen(volumen);
        this.setColor(color);
        this.setRendimiento(rendimiento);
    }

    @Override
    public Double recargo(){
        return rendimiento > 8.00 ? 1.25 : 1.00;
    };
}
