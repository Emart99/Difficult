package com.main.Difficult.Repositories.mysql;

import com.main.Difficult.Domain.usuario.Factura;
import com.main.Difficult.Domain.usuario.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @EntityGraph(attributePaths= {"facturas"})
    <S extends Usuario> List<S> saveAll(Iterable<S> entities);

    @Query("SELECT u.id FROM Usuario u " +
        "WHERE u.usuarioNombre = :usuarioNombre AND u.contrasenia = :contrasenia")
    Optional<Long> findByUsuarioNombreAndContrasenia(@Param("usuarioNombre") String usuarioNombre, @Param("contrasenia") String contrasenia);

    @Query(
       "SELECT usuario from Usuario usuario "+
       "where usuario.id = :uid ")
    Optional<Usuario> findById(@Param("uid") Long id);

    @Query(
       "SELECT factura from Usuario usuario "+
       "join usuario.facturas as factura "+
       "where usuario.id = :uid "+
       "order by factura.fechaDeCompra desc")
    List<Factura>  findFacturasByUserId(@Param("uid")Long id, Pageable page);

    @Query(
       "SELECT factura from Usuario usuario "+
       "join usuario.facturas as factura "+
       "where usuario.id = :uid " +
       "order by factura.fechaDeCompra desc ")
    List<Factura> findAllFacturasByUserId(@Param("uid") Long id);

    @Query(
        "SELECT usuario from Usuario usuario "+
        "join fetch usuario.facturas "+
        "where usuario.id = :uid ")
    Optional<Usuario> findUsuarioAndFacturaByUsuarioId(@Param("uid") Long id);

}
