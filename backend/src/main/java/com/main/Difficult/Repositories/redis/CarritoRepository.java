package com.main.Difficult.Repositories.redis;

import com.main.Difficult.Domain.carrito.Carrito;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarritoRepository extends CrudRepository<Carrito,Long> {
    Optional<Carrito> findById(Long uid);
}
