package aplicacion.repositorios

import aplicacion.dominio.carrito.ItemCarrito
import org.springframework.stereotype.Component

@Component
open class RepoCarrito {
    private var listaDeItems: MutableMap<Long, MutableList<ItemCarrito>> = mutableMapOf()

    fun agregarItem(uid: Long, itemCarrito: ItemCarrito) {
        val carrito = listaDeItems.getOrPut(uid) { mutableListOf() }
        val item = carrito.firstOrNull { it == itemCarrito }

        if (item == null) {
            carrito.add(itemCarrito)
        } else {
            item.updateCantidad(itemCarrito)
        }
    }

    fun quitarItem(uid: Long, itemId: Long) {
        listaDeItems[uid]?.let {
            it.removeIf { x -> x.id == itemId }
            if (it.isEmpty()) listaDeItems.remove(uid)
        }
    }

    fun limpiarCarrito(uid: Long) {
        listaDeItems.remove(uid)
    }

    fun items(uid: Long): MutableList<ItemCarrito> {
        return listaDeItems[uid] ?: mutableListOf()
    }

    fun carritoDelUsuario(uid: Long): MutableList<ItemCarrito>? {
        return listaDeItems[uid]
    }
}