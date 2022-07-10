package com.main.Difficult.Domain.producto.simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter @Setter
@Document("Producto")
@TypeAlias("Piso")
public class Piso extends ProductoSimple {
    @JsonProperty("medidas_x") private Double medidasX = 0.00;
    @JsonProperty("medidas_y") private Double medidasY = 0.00;
    private Transito transito = Transito.NORMAL;
    private Terminacion terminacion = Terminacion.BRILLANTE;

    public Piso(String imagen, String nombre, String descripcion, Integer puntaje, String paisDeOrigen, Double precioBase, Double medidasX,Double medidasY, Transito transito, Terminacion terminacion) {
        super(imagen, nombre, descripcion, puntaje, paisDeOrigen, precioBase);
        this.setMedidasX(medidasX);
        this.setMedidasY(medidasY);
        this.setTransito(transito);
        this.setTerminacion(terminacion);
    }

    @Override
    public Double recargo() {
        return transito.getRecargo();
    }
}
