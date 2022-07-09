package com.main.Difficult.Controllers;

import com.main.Difficult.Deserializers.UsuarioActualizacionDes;
import com.main.Difficult.Deserializers.UsuarioCreacionDes;
import com.main.Difficult.Deserializers.UsuarioLoginDes;
import com.main.Difficult.Domain.usuario.Usuario;
import com.main.Difficult.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
public class UsuarioController {
    @Autowired private UsuarioService usuarioService;
    @PostMapping("/usuario/registrar")
    public void create(@Validated @RequestBody UsuarioCreacionDes usuario){
        usuarioService.create(new Usuario (usuario.nombre, usuario.apellido,usuario.edad,usuario.saldo, usuario.usuarioNombre, usuario.contrasenia,usuario.imagen));
    }
    @PutMapping("/usuario/ingresar")
    public Long findByUsuarioNombreAndContrasenia(@RequestBody UsuarioLoginDes usuario){
        return usuarioService.findByUsuarioNombreAndContrasenia(usuario.usuarioNombre,usuario.contrasenia);
    }
    @GetMapping("/usuario/perfil/{uid}")
    public Usuario findById(@PathVariable Long uid){
        return usuarioService.findById(uid);
    }

    @PutMapping("/usuario/perfil/{uid}/editar")
    public void update (@RequestBody UsuarioActualizacionDes usuario, @PathVariable Long uid){
        usuarioService.update(uid,usuario);
    }
}
