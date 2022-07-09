package com.main.Difficult.Deserializers;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class UsuarioCreacionDes {
    @NotEmpty public String usuarioNombre  = "";
    @NotEmpty public String contrasenia  = "";
    @NotEmpty public String imagen  = "";
    @NotEmpty public String nombre  = "";
    @NotEmpty public String apellido  = "";
    @Positive public Integer edad  = 0;
    @Positive public Double saldo  = 0.0;
}