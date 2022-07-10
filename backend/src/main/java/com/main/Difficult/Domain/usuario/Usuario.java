package com.main.Difficult.Domain.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.main.Difficult.Domain.usuario.Factura;
import com.main.Difficult.Exceptions.BusinessException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@Entity @Node("Usuario")
public class Usuario {
    @org.springframework.data.neo4j.core.schema.Id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore private Long id = null;
    @JsonIgnore private String usuarioNombre = "";
    @JsonIgnore private String contrasenia = "";
    private String nombre = null;
    private String apellido = null;
    private Double saldo = null;
    private String imagen = "";
    private Integer edad = 0;
    @JsonProperty("comprasRealizadas")
    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}) @JoinColumn(name="usuario_id")
    @org.springframework.data.annotation.Transient
    private List<Factura> facturas = new ArrayList<>();
    public Usuario(String _nombre,
                   String _apellido,
                   Integer _edad,
                   Double _saldo,
                   String _usuarioNombre ,
                   String _contrasenia,
                   String _imagen )
    {
        setNombre(_nombre);
        setApellido(_apellido);
        setEdad(_edad);
        setSaldo(_saldo);
        setUsuarioNombre(_usuarioNombre);
        setContrasenia(_contrasenia);
        setImagen(_imagen);
    }
    public Usuario(){}
    public void comprar(Factura factura){
        factura.setUser(this);
        this.checkSaldo(factura);
        saldo = this.calcularSaldo(factura);
        facturas.add(factura);
    }
    private Double calcularSaldo(Factura factura){
        if( this.saldo != null){
            return this.saldo - factura.getImporteTotal();
        }
        else{
            return 0.00;
        }
    }
    private void checkSaldo(Factura factura){
        if(this.calcularSaldo(factura)< 0.0){
            throw new BusinessException("Saldo insuficiente para la compra");
        }
    }
}
