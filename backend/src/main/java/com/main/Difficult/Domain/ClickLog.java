package com.main.Difficult.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter @Setter
@Document("ClickLog")
public class ClickLog {
    @Id private String id;
    private Long idUsuario;
    private String idProducto;
    private LocalDateTime fechaClick;
    private String productoImagen;
    private String productoNombre;
    private String productoDescripcion;
    private Integer productoValoracion;

    public ClickLog(String idProducto, Long idUsuario, String productoImagen, String productoNombre, String productoDescripcion, Integer productoValoracion){
        this.setIdProducto(idProducto);
        this.setIdUsuario(idUsuario);
        this.setProductoImagen(productoImagen);
        this.setProductoNombre(productoNombre);
        this.setProductoDescripcion(productoDescripcion);
        this.setProductoValoracion(productoValoracion);
    }
}
