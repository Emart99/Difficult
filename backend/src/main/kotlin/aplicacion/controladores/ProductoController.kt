package aplicacion.controladores

import aplicacion.dominio.producto.DatosDeBusqueda
import aplicacion.dominio.producto.Producto
import aplicacion.dominio.producto.View
import aplicacion.servicios.ProductoService
import com.fasterxml.jackson.annotation.JsonView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], methods = [RequestMethod.GET])
class ProductoController {
    @Autowired private lateinit var productoService: ProductoService

    @JsonView(View.ProductoIndividual::class)
    @GetMapping("/producto/{id}")
    fun findById(
        @PathVariable id: Long
    ): Producto {
        return productoService.findById(id)
    }

    @JsonView(View.ProductoLista::class)
    @GetMapping("/producto/traer")
    fun buscarPor(
        @RequestParam(value = "nombre", required = false) nombre: String?,
        @RequestParam(value = "puntaje", required = false) puntaje: Int?,
        @RequestParam(value = "paisDeOrigen", required = false) paisDeOrigen: String?
    ): List<Producto> {
        val datos = DatosDeBusqueda(nombre, puntaje, paisDeOrigen)
        return productoService.buscarPor(datos)
    }
}