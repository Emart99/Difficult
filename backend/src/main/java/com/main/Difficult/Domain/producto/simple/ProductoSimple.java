package com.main.Difficult.Domain.producto.simple;

import com.main.Difficult.Domain.producto.Producto;

public abstract class ProductoSimple extends Producto {
    public ProductoSimple(String _image, String _name, String _descripcion, Integer _puntaje, String _paisDeOrigen, Double _precioBase) {
        super(_image, _name, _descripcion, _puntaje, _paisDeOrigen, _precioBase);
    }

    @Override
    public Double precio(){
        return super.precio() * this.recargo();
    }

    abstract public Double recargo();
}
