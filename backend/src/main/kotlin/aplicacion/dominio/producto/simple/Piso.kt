package aplicacion.dominio.producto.simple

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import aplicacion.dominio.producto.View
import javax.persistence.Entity

@Entity
class Piso : ProductoSimple() {
    @JsonProperty("medidas_x") var medidasX: Double = 0.00
    @JsonProperty("medidas_y") var medidasY: Double = 0.00
    @JsonView(View.ProductoIndividual::class) var transito: Transito = Transito.NORMAL
    @JsonView(View.ProductoIndividual::class) var terminacion: Terminacion = Terminacion.BRILLANTE

    override fun recargo(): Double {
        return transito.recargo()
    }
}

enum class Transito {
    ALTO_TRANSITO {
        override fun recargo(): Double = 1.20
    },
    NORMAL {
        override fun recargo(): Double = 1.00
    };

    abstract fun recargo(): Double
}

enum class Terminacion {
    SATINADO, SEMI_SATINADO, BRILLANTE
}