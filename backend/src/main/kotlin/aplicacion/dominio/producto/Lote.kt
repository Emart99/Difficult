package aplicacion.dominio.producto

import com.fasterxml.jackson.annotation.JsonView
import aplicacion.excepciones.BusinessException
import java.time.LocalDate
import javax.persistence.*

@Entity
@JsonView(View.ProductoIndividual::class)
class Lote {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long = 0L
    var fechaIngreso: LocalDate = LocalDate.now()
    var cantidadDeUnidades: Int = 0

    fun tieneMasDe4Meses(): Boolean {
        return fechaIngreso.plusMonths(4).isAfter(LocalDate.now())
    }

    fun descontar(cantidad: Int) {
        this.checkStock(cantidad)
        cantidadDeUnidades -= cantidad
    }

    private fun checkStock(cantidad: Int) {
        if (cantidadDeUnidades < cantidad) throw BusinessException(
            "la compra excede en ${cantidad - cantidadDeUnidades} el stock disponible en el lote $id"
        )
    }
}