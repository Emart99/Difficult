package aplicacion.dominio.producto.compuesto

import aplicacion.dominio.Item
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import aplicacion.dominio.producto.Lote
import aplicacion.dominio.producto.Producto
import aplicacion.dominio.producto.View
import aplicacion.dominio.producto.simple.ProductoSimple
import javax.persistence.*

@Entity
class Combo : Producto() {
    @JsonView(View.ProductoIndividual::class)
    @OneToMany(cascade = [CascadeType.MERGE,CascadeType.PERSIST], fetch = FetchType.EAGER) @JoinColumn(name = "combo_id")
    @JsonIgnore var items: MutableSet<ItemCombo> = mutableSetOf()

    fun agregarProductoCombo(item: ProductoSimple, lote: Lote, cantidad: Int) {
        items.add(ItemCombo(item, lote, cantidad))
    }

    fun borrarProductoCombo(item: ProductoSimple) {
        items.removeIf { it.producto.id == item.id }
    }

    override fun hayProductoConMasDe4Meses(): Boolean {
        return super.hayProductoConMasDe4Meses() || hayItemsConMasDe4Meses()
    }

    private fun hayItemsConMasDe4Meses(): Boolean {
        return items.any { it.tieneMasDe4Meses() }
    }

    override fun precio(): Double {
        return (0.85 * (items.sumOf { it.precioBase() } + 20 * items.size)) * descuento()
    }
}

/* PRODUCTOSIMPLE - LOTE - COMBO */
@JsonView(View.ProductoIndividual::class)
@Entity @Table(name="combo_producto_lote")
class ItemCombo():Item(){

    constructor(producto: ProductoSimple, lote: Lote, cantidad: Int) : this() {
        this.producto = producto
        this.lote = lote
        this.cantidad = cantidad
    }

    fun precioBase(): Double {
        return producto.precioBase
    }

    fun tieneMasDe4Meses(): Boolean {
        return lote.tieneMasDe4Meses()
    }

    @JsonProperty fun producto(): String = producto.nombre
    @JsonProperty("Precio") fun productoPrecio(): String = "$"+producto.precioBase.toString()
    @JsonProperty fun lote(): String = lote.id.toString()
}