package aplicacion.deserealizadores

import javax.validation.constraints.Positive

class ItemCarritoDes {
    @Positive var productoId: Long = 0
    @Positive var loteId: Long = 0
    @Positive var cantidad: Int = 0
}