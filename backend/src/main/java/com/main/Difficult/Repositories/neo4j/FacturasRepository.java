package com.main.Difficult.Repositories.neo4j;

import com.main.Difficult.Domain.usuario.Factura;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface FacturasRepository extends Neo4jRepository<Factura,Long> {
    @Query("match (p:Producto{id: $idProducto})--(a:Articulo)--(f:Factura)--(a2:Articulo)-->(productos:Producto)" +
            "where exists {" +
            "match (p:Producto{id: $idProducto})--(a:Articulo)--(f:Factura)--(u:Usuario) " +
            "where not u.id = $idUsuario" +
            "} " +
            "return productos.id"
    )
    public Iterable<String> recomendacionProductosByProductoId(String idProducto, Long idUsuario);

    @Query("match (p:Producto{id: $idProducto})--(a:Articulo)--(f:Factura)--(a2:Articulo)-->(productos:Producto)" +
            "return productos.id"
    )
    public Iterable<String> recomendacionProductosByProductoIdSinUsuario(String idProducto);
}
