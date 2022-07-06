package com.main.Difficult.Controllers;

import com.main.Difficult.Deserializers.UsuarioActualizacionDes;
import com.main.Difficult.Deserializers.UsuarioLoginDes;
import com.main.Difficult.Domain.Usuario;
import com.main.Difficult.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    /*@PostMapping("/usuario/registrar")
    public void create(@Validated @RequestBody Usuario usuario){
        usuarioService.create(usuario);
    }*/
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
