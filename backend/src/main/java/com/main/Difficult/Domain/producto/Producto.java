package com.main.Difficult.Domain.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@JsonView(View.ProductoLista.class)
@Document("Producto")
@Getter @Setter
public abstract class Producto {
    @Id
    @JsonView(View.ProductoRecomendado.class) private String id;
    @JsonView(View.ProductoRecomendado.class) private String imagen;
    @JsonView(View.ProductoRecomendado.class) private String nombre;
    @JsonView(View.ProductoRecomendado.class) private String descripcion;
    private Integer puntaje = 0;
    private String paisDeOrigen = "";
    @JsonIgnore  private Double precioBase = 0.0;
    @JsonProperty @JsonView(View.ProductoConLote.class)
    private Set<Lote> lotes = new HashSet<>() ;

    public Producto(String imagen, String nombre, String descripcion, Integer puntaje, String paisDeOrigen, Double precioBase){
        this.setImagen(imagen);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setPuntaje(puntaje);
        this.setPaisDeOrigen(paisDeOrigen);
        this.setPrecioBase(precioBase);
    }

    public Lote lotePorId(Long loteId){
        return lotes.stream()
                .filter(lote->lote.getId().equals(loteId))
                .findAny()
                .orElse(null);
    }
    public void agregarLote(Lote lote){
        lotes.add(lote);
    }
    @JsonProperty
    @JsonView(View.ProductoRecomendado.class)
    public Double precio(){
        return precioBase * this.descuento();
    }
    public Double descuento(){
        return this.hayProductoConMasDe4Meses() ?  0.90 : 1;
    }
    public Boolean hayProductoConMasDe4Meses(){
        return lotes.stream().anyMatch(lote-> lote.tieneMasDe4Meses());
    }
    @JsonProperty public String tipo(){
        return this.getClass().getSimpleName();
    }

}
