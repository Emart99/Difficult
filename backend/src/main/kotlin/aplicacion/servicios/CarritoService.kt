package aplicacion.servicios

import aplicacion.dominio.carrito.ItemCarrito
import aplicacion.dominio.usuario.Factura
import aplicacion.dominio.usuario.ItemFactura
import aplicacion.excepciones.CarritoExcepcion
import aplicacion.repositorios.RepoCarrito
import aplicacion.repositorios.RepoProductos
import aplicacion.repositorios.RepoUsuarios
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CarritoService {
    @Autowired
    private lateinit var repoCarrito: RepoCarrito
    @Autowired
    private lateinit var repoUsuarios: RepoUsuarios
    @Autowired
    private lateinit var repoProductos: RepoProductos

    fun agregarItem(uid: Long, itemCarrito: ItemCarrito) {
        this.validarUsuario(uid)
        repoCarrito.agregarItem(uid, itemCarrito)
    }

    fun quitarItem(uid: Long, itemId: Long) {
        this.validarUsuario(uid)
        repoCarrito.quitarItem(uid, itemId)
    }

    fun limpiarCarrito(uid: Long) {
        this.validarUsuario(uid)
        repoCarrito.limpiarCarrito(uid)
    }

    fun items(uid: Long): MutableList<ItemCarrito> {
        this.validarUsuario(uid)
        try {
            val items = repoCarrito.items(uid)
            this.updateItems(items)
            return repoCarrito.items(uid)
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun finalizarCompra(uid: Long) {
        this.validarUsuario(uid)
        try {

            val user = repoUsuarios.findById(uid).get()
            user.facturas = repoUsuarios.findAllFacturasByUserId(uid) as MutableList<Factura>
            repoCarrito.carritoDelUsuario(uid)?.let {
                user.comprar(Factura(crearItemsFacturas(it)))
                repoUsuarios.save(user)
                repoCarrito.limpiarCarrito(uid)
            }
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    private fun updateItems(itemsCarrito: MutableList<ItemCarrito>) {
        val productos = repoProductos.findAllById(itemsToIds(itemsCarrito))
        itemsCarrito.map { ic -> ic.updateItem(productos) }
    }

    private fun validarUsuario(uid: Long) {
        if (!repoUsuarios.existsById(uid)) throw CarritoExcepcion("La solicitud requiere autenticación")
    }

    private fun crearItemsFacturas(itemsCarrito: MutableList<ItemCarrito>): List<ItemFactura> {
        val productos = repoProductos.findAllById(itemsToIds(itemsCarrito))
        return itemsCarrito.map { ic -> ic.itemFactura(productos) }
    }

    private fun itemsToIds(itemsCarrito: MutableList<ItemCarrito>): List<Long> {
        return itemsCarrito.map { it.productoId }
    }
}