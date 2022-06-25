import axios from "axios";
import { REST_SERVER_URL } from "../REST_SERVER_URL";
import { obtenerId } from "./usuarioService";

export async function productoMasClickeadoPorUsuario(){
    const userId = obtenerId();
  const response = await axios.get(
    REST_SERVER_URL + `/logs/${userId}`
  );
  return response.data;
}

export async function logClickProducto(producto){
    const userId = obtenerId();
    const response = await axios.post(
    REST_SERVER_URL + `/logs/`, {
        idUsuario: userId,
        idProducto: producto.id,
        productoImagen:producto.imagen,
        productoNombre:producto.nombre,
        productoDescripcion:producto.descripcion,
        productoValoracion:producto.puntaje
    }
  );
  return response.data;
}

export async function productosMasVisitados(){
  const response = await axios.get(REST_SERVER_URL+"/logs/productosMasClickeados")
  return response.data
}