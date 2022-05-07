package aplicacion.dominio.producto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import javax.persistence.*

@JsonView(View.ProductoLista::class)
@Entity @Inheritance
abstract class Producto {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    open var id: Long? = null
    open var imagen: String = ""

    open var nombre: String = ""
    open var descripcion: String = ""
    open var puntaje: Int = 0
    open var paisDeOrigen: String = ""

    @JsonIgnore
    open var precioBase: Double = 0.0


    @JsonView(View.ProductoIndividual::class)
    @OneToMany(cascade = [CascadeType.MERGE,CascadeType.PERSIST]) @JoinColumn(name="producto_id")
    open var lotes: MutableSet<Lote> = mutableSetOf()

    fun agregarLote(lote: Lote) {
        lotes.add(lote)
    }

    fun quitarLote(lote: Lote) {
        lotes.remove(lote)
    }

    fun lotePorId(liteId: Long): Lote {
        return lotes.first { it.id == liteId }
    }

    @JsonProperty
    open fun precio(): Double {
        return precioBase * descuento()
    }

    open fun descuento(): Double {
        return if (hayProductoConMasDe4Meses()) 0.90 else 1.00
    }


    open fun hayProductoConMasDe4Meses(): Boolean {
        return lotes.any { it.tieneMasDe4Meses() }
    }

    @JsonProperty
    fun tipo(): String? {
        return this::class.simpleName
    }
}