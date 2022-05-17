package aplicacion.dominio.carrito

import aplicacion.Keys
import aplicacion.dominio.producto.Lote
import aplicacion.dominio.producto.Producto
import aplicacion.dominio.usuario.ItemFactura

class ItemCarrito {
    var id: Long = Keys.plusItemCarrito()
    var productoId: Long = 0
    var loteId: Long = 0
    var cantidad: Int = 0
    var imagen: String = ""
    var precio: Double = 0.0
    var nombre: String = ""
    var descripcion: String = ""

    fun itemFactura(lista: List<Producto>): ItemFactura {
        val producto: Producto = lista.first { it.id == productoId }
        val lote: Lote = producto.lotePorId(loteId)

        return ItemFactura(producto, lote, cantidad)
     }

    fun updateCantidad(item: ItemCarrito) {
        this.cantidad += item.cantidad
    }

    fun updateItem(lista: List<Producto>) {
        val producto: Producto = lista.first { it.id == productoId }
        imagen = producto.imagen
        precio = cantidad * producto.precio()
        nombre = producto.nombre
        descripcion = producto.descripcion
    }

    override fun equals(other: Any?): Boolean {
        return other is ItemCarrito && other.productoId == productoId && other.loteId == loteId
    }


    override fun hashCode(): Int {
        var result = productoId.hashCode()
        result = 31 * result + loteId.hashCode()
        result = 31 * result + cantidad
        return result
    }
}