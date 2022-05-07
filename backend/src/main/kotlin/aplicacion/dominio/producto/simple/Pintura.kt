package aplicacion.dominio.producto.simple

import com.fasterxml.jackson.annotation.JsonView
import aplicacion.dominio.producto.View
import javax.persistence.Entity

@Entity
class Pintura : ProductoSimple() {
    var volumen: Double = 0.0
    var color: String = "#008000"
    @JsonView(View.ProductoIndividual::class) var rendimiento: Double = 0.0

    override fun recargo(): Double {
        return if (rendimiento > 8.00) 1.25 else 1.00
    }
}