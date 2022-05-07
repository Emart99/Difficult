import axios from "axios";
import { carritoDTO } from "../dominio/carritoDTO";
import { REST_SERVER_URL } from "../REST_SERVER_URL";
import { obtenerId } from "./usuarioService";

export async function traerCarrito() {
  const userId = obtenerId();
  const response = await axios.get(
    REST_SERVER_URL + `/carrito/${userId}/items`
  );
  return response.data;
}

export async function agregarAlCarrito(productoId, loteId, cantidad) {
  const userId = obtenerId();
  const item = new carritoDTO(productoId, loteId, cantidad);
  await axios.post(REST_SERVER_URL + `/carrito/${userId}/agregar`, item);
}

export async function borrarDelCarrito(itemId) {
  const userId = obtenerId();
  await axios.delete(
    REST_SERVER_URL + `/carrito/${userId}/quitar/${itemId}`
  );
}

export async function limpiarCarrito() {
  const userId = obtenerId();
  await axios.delete(REST_SERVER_URL + `/carrito/${userId}/limpiar`);
}

export async function comprarCarrito() {
  const userId = obtenerId();
  return await axios.post(REST_SERVER_URL + `/carrito/${userId}/comprar`);
}
