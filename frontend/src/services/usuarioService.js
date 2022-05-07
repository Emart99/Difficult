import axios from "axios";
import { UsuarioDTO } from "../dominio/usuarioDTO";
import { REST_SERVER_URL } from "../REST_SERVER_URL";

const STORAGE = window.localStorage
export function logout() {
  STORAGE.removeItem('user_id');
}

export function obtenerId() {
    return STORAGE.getItem('user_id');
}

export function estaLogeado() {
    return STORAGE.getItem('user_id') !== null;
}

export async function logear(username,contrasenia) {
    const _usuario={
        usuarioNombre:username,
        contrasenia:contrasenia
    }
    const response = await axios.put(REST_SERVER_URL+ '/usuario/ingresar', _usuario)
    STORAGE.setItem('user_id', await response.data)
}

export async function traerUsuario() {
    const id = obtenerId()
    const response = await axios.get(REST_SERVER_URL +`/usuario/perfil/${id}`);
    return response.data
}

export async function crearUsuario(nombre,apellido,usuario,contrasenia,edad){
    const _usuario={
        usuarioNombre:usuario,
        contrasenia:contrasenia,
        imagen:' ',
        nombre:nombre,
        apellido:apellido,
        edad:edad,
        saldo:1.0,
    }
    const response = await axios.post(REST_SERVER_URL + '/usuario/registrar',_usuario)
    return response.data
}

export async function guardarUsuario(user) {
    const id = obtenerId()
    await axios.put(REST_SERVER_URL + `/usuario/perfil/${id}/editar`, new UsuarioDTO(user.nombre,user.apellido,user.saldo))
}
