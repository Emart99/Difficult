package com.main.Difficult;

import com.main.Difficult.Domain.Usuario;
import com.main.Difficult.Repositories.mysql.UsuarioRepository;
import com.main.Difficult.Repositories.neo4j.FacturasRepository;
import com.main.Difficult.Repositories.neo4j.UsuarioGrafosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DifficultBootstrap implements InitializingBean {
    @Autowired private UsuarioRepository repoUsuario;
    @Autowired private UsuarioGrafosRepository repoUsuarioGrafos;
    @Autowired private FacturasRepository repoFacturas;

    private List<Usuario> usuarios = new ArrayList<>();
    private Logger log = LoggerFactory.getLogger(DifficultBootstrap.class);

    private void instanciarUsuarios(){
        usuarios.add(new Usuario(
                "Clemente",
                "Lopez",
                34,
                120000.00,
                "Clemente",
                "1234",
                "LziS4xI"));
    }

    private void iniciarDatos(){
        this.instanciarUsuarios();
        repoUsuario.saveAll(usuarios);
        repoUsuarioGrafos.saveAll(usuarios);
    }
    @Override @PostConstruct
    public void afterPropertiesSet() throws Exception{
        log.info("**********************************************************************************************");
        log.info("Running initialization");
        log.info("**********************************************************************************************");
        this.iniciarDatos();
        log.info("**********************************************************************************************");
        log.info("Finish initialization");
        log.info("**********************************************************************************************");
    }
}
