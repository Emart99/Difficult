package com.main.Difficult.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("ClickLog")
public class ClickLog {
    @Id public String id;
    @Getter @Setter private Long idUsuario;
    @Getter @Setter private String idProducto;
    @Getter @Setter private LocalDateTime fechaClick;
    @Getter @Setter private String productoImagen;
    @Getter @Setter private String productoNombre;
    @Getter @Setter private String productoDescripcion;
    @Getter @Setter private Integer productoValoracion;

    public ClickLog(String idProducto, Long idUsuario, String productoImagen, String productoNombre, String productoDescripcion, Integer productoValoracion){
        this.setIdProducto(idProducto);
        this.setIdUsuario(idUsuario);
        this.setProductoImagen(productoImagen);
        this.setProductoNombre(productoNombre);
        this.setProductoDescripcion(productoDescripcion);
        this.setProductoValoracion(productoValoracion);
    }
}
