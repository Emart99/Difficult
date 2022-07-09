package com.main.Difficult.Domain.producto.simple;

public enum Transito {
    ALTO_TRANSITO(1.20),
    NORMAL(1.00);
    public Double recargo;
    private Transito(Double _recargo){
        this.recargo = _recargo;
    }
    public Double getRecargo(){
        return this.recargo;
    }
}
