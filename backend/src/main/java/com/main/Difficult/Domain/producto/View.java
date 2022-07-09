package com.main.Difficult.Domain.producto;

public interface View {
    public interface ProductoRecomendado{}
    public interface ProductoLista extends ProductoRecomendado{}
    public interface ProductoIndividual extends ProductoLista{}
    public interface ProductoConLote extends ProductoIndividual , Lote{}
    public interface ProductoItem extends ProductoConLote ,Item{}
    public interface Item{}
    public  interface Lote{}
    public interface DetalleDeProducto extends ProductoConLote,ProductoItem{}

}
