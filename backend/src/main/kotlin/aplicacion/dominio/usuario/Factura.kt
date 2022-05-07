package aplicacion.dominio.usuario

import aplicacion.dominio.Item
import aplicacion.dominio.producto.Lote
import aplicacion.dominio.producto.Producto
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Factura() {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    var ordenDeCompra: Long? = null
    var cantidadDeArticulos: Int = 0
    var importeTotal: Double = 0.00

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var fechaDeCompra: LocalDateTime = LocalDateTime.now()

    @OneToMany(cascade = [CascadeType.MERGE,CascadeType.PERSIST]) @JoinColumn(name="factura_id")
    @JsonIgnore lateinit var items: List<ItemFactura>

    constructor(items: List<ItemFactura>) : this() {
        this.items = items

        this.cantidadDeArticulos = items.size
        this.importeTotal = items.sumOf { it.precioCompra }
    }
}

@Entity @Table(name="factura_producto_lote")
class ItemFactura(): Item() {
    var precioCompra: Double = 0.0
    constructor(producto: Producto, lote: Lote, cantidad: Int) : this() {
        this.producto = producto
        this.lote = lote
        this.cantidad = cantidad

        lote.descontar(cantidad)
        precioCompra = cantidad * producto.precio()
    }
}