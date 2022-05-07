package aplicacion.servicios

import aplicacion.dominio.producto.DatosDeBusqueda
import aplicacion.dominio.producto.Producto
import aplicacion.excepciones.NotFoundException
import aplicacion.repositorios.RepoProductos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ProductoService {
    @Autowired private lateinit var repoProductos: RepoProductos
    @Transactional(readOnly = true)
    open fun findById(id: Long): Producto {
        return repoProductos.findById(id).orElseThrow {
            throw NotFoundException("Producto inexistente")
        }
    }
    @Transactional(readOnly = true)
    open fun buscarPor(datosDeBusqueda: DatosDeBusqueda): List<Producto> {
        return repoProductos.buscarPor(datosDeBusqueda)
    }
}

