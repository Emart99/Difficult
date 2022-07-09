package com.main.Difficult.Repositories.neo4j;

import com.main.Difficult.Domain.usuario.Usuario;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UsuarioGrafosRepository extends Neo4jRepository<Usuario,Long> {}
