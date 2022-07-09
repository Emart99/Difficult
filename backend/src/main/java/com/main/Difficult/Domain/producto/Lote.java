package com.main.Difficult.Domain.producto;

import com.fasterxml.jackson.annotation.JsonView;
import com.main.Difficult.Exceptions.BusinessException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Lote {
    @Id
    @JsonView(View.Lote.class) public Long id ;
    @JsonView(View.Lote.class) @Getter @Setter private LocalDate fechaIngreso = LocalDate.now();
    @JsonView(View.Lote.class) @Getter @Setter private Integer cantidadDeUnidades = 0;

    public Lote(Long id, LocalDate fechaIngreso, Integer cantidadDeUnidades){
        this.id = id;
        this.setFechaIngreso(fechaIngreso);
        this.setCantidadDeUnidades(cantidadDeUnidades);
    }

    public Boolean tieneMasDe4Meses(){
        return fechaIngreso.plusMonths(4).isAfter(LocalDate.now());
    }

    public void descontar(Integer _cantidad){
        this.checkStock(_cantidad);
        this.cantidadDeUnidades -= _cantidad;
    }

    private void checkStock(Integer _cantidad){
        if(this.cantidadDeUnidades < _cantidad){
            throw new BusinessException("la compra excede el stock disponible");
        }
    }

}
