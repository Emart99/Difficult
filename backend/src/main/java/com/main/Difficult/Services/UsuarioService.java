package com.main.Difficult.Services;

import com.main.Difficult.Deserializers.UsuarioActualizacionDes;
import com.main.Difficult.Domain.usuario.Usuario;
import com.main.Difficult.Exceptions.NotFoundException;
import com.main.Difficult.Repositories.mysql.UsuarioRepository;
import com.main.Difficult.Repositories.neo4j.UsuarioGrafosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repoUsuarios;
    @Autowired
    private UsuarioGrafosRepository repoUsuariosGrafos;

    @Transactional
    public void create(Usuario usuario){
        repoUsuarios.save(usuario);
        repoUsuariosGrafos.save(usuario);
    }

    @Transactional(readOnly = true)
    public Long findByUsuarioNombreAndContrasenia(String usuarioNombre, String contrasenia){
        return repoUsuarios.findByUsuarioNombreAndContrasenia(usuarioNombre,contrasenia)
                .orElseThrow(()-> new NotFoundException("Usuario inexistente"));
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long uid){
        var user =  repoUsuarios.findById(uid).get();
        user.setFacturas( repoUsuarios.findFacturasByUserId(uid, PageRequest.of(0, 5)));
        return user;
    }

    @Transactional
    public void update(Long uid, UsuarioActualizacionDes usuario){
        try{
            var usuarioAEditar =repoUsuarios.findById(uid).get();
            if (usuario.nombre != "") {usuarioAEditar.setNombre(usuario.nombre);};
            if (usuario.apellido != "") {usuarioAEditar.setApellido(usuario.apellido);}
            if (usuario.saldo > 0){usuarioAEditar.setSaldo(usuario.saldo);};
            repoUsuarios.save(usuarioAEditar);
            repoUsuariosGrafos.save(usuarioAEditar);
        }
        catch (Exception e){
            throw new NotFoundException("Ocurrió un error mientras se intentaba actualizar los datos de usuario");
        }
        }

}
