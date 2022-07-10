package com.main.Difficult.Domain.carrito;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@RedisHash("Carrito")
public class Carrito {
    @Id @Indexed
    public long id = 0;
    public List<ItemCarrito>  items = new ArrayList<>();

    public Carrito(Long uid){
        this.id = uid;
    }
    public Carrito(){}
}
