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
public abstract class Producto {
    @Id
    @JsonView(View.ProductoRecomendado.class) public String id;
    @JsonView(View.ProductoRecomendado.class) @Getter @Setter private String imagen;
    @JsonView(View.ProductoRecomendado.class) @Getter @Setter private String nombre;
    @JsonView(View.ProductoRecomendado.class) @Getter @Setter  private String descripcion;
    @Getter @Setter private Integer puntaje = 0;
    @Getter @Setter private String paisDeOrigen = "";
    @JsonIgnore  @Getter @Setter  private Double precioBase = 0.0;
    @JsonProperty @JsonView(View.ProductoConLote.class)
    @Getter @Setter private Set<Lote> lotes = new HashSet<>() ;

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
                .filter(lote->lote.id.equals(loteId))
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

}
