package aplicacion.repositorios

import aplicacion.dominio.producto.DatosDeBusqueda
import aplicacion.dominio.producto.Producto
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RepoProductos : CrudRepository<Producto, Long> {

    @EntityGraph(attributePaths = ["lotes"])
    override fun <S : Producto?> saveAll(entities: MutableIterable<S>): MutableIterable<S>


    @Query("""
        SELECT DISTINCT producto from Producto producto 
            join fetch
               producto.lotes
            where
               producto.id in :ids
    """)
    override fun findAllById(ids: Iterable<Long>): List<Producto>


    @Query("""
            SELECT producto from Producto producto 
            join fetch
               producto.lotes
            where
                producto.id = :pid
    """)
    override fun findById(@Param("pid") id: Long): Optional<Producto>


    @Query("""
        SELECT DISTINCT producto from Producto producto 
            join fetch
               producto.lotes
        WHERE
            (:#{#datos.nombre}       = NULL OR producto.nombre LIKE %:#{#datos.nombre}%) AND
            (:#{#datos.puntaje}      = NULL OR producto.puntaje >= :#{#datos.puntaje}) AND
            (:#{#datos.paisDeOrigen} = NULL OR producto.paisDeOrigen = :#{#datos.paisDeOrigen})
    """)
    fun buscarPor(@Param("datos") datosDeBusqueda: DatosDeBusqueda): List<Producto>
}