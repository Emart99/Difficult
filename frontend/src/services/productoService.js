import axios from "axios";
import { filterDTO } from '../dominio/filterDTO';
import { REST_SERVER_URL } from "../REST_SERVER_URL";
import { obtenerId } from "./usuarioService";

export async function obtenerProductoById(_id) { 
  const response = await axios.get(REST_SERVER_URL + `/producto/${_id}`);
  return response.data;
}

export async function obtenerProductoFilter(texto, _puntaje, pais) {
  const queryString = require("query-string");
  const query = queryString.stringify(new filterDTO(texto, _puntaje, pais), {
    skipNull: true,
  });
  const response = await axios.get(
    REST_SERVER_URL + `/producto/traer?` + query
  );
  return response.data;
}

export async function obetenerRecomendaciones(pid){
  const uid = obtenerId();
  let response
  if (uid){
    response = await axios.get(REST_SERVER_URL + `/producto/${pid}/usuario/${uid}`);
  }else{
    response = await axios.get(REST_SERVER_URL + `/producto/${pid}/usuario`);
  }
  return response.data;
}