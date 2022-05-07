// import React from "react";
// import axios from "axios";
// import { render, fireEvent } from "@testing-library/react";
// import userEvent from "@testing-library/user-event";
// import { obtenerProductoFilter } from "../services/productoService";
// import { Home } from "../componentes/vistas/Home";
// import { REST_SERVER_URL } from "../REST_SERVER_URL";

// const JSON_HOME = [
//   {
//     id: 1,
//     imagen: "099",
//     nombre: "combo",
//     descripcion: "pisos y pinturas",
//     puntaje: 1,
//     paisDeOrigen: "Argentina",
//     precioBase: 4714.099999999999,
//     precio: 4242.69,
//     tipo: "Combo",
//   },
//   {
//     id: 2,
//     imagen: "001",
//     nombre: "Cañuelas",
//     descripcion: "Cerámica de interior",
//     puntaje: 0,
//     paisDeOrigen: "Argentina",
//     precio: 1007.1,
//     tipo: "Piso",
//     medidas_x: 50,
//     medidas_y: 50,
//   },
//   {
//     id: 3,
//     imagen: "002",
//     nombre: "Lourdes",
//     descripcion: "Porcelanatos",
//     puntaje: 1,
//     paisDeOrigen: "Argentina",
//     precio: 1813.3200000000002,
//     tipo: "Piso",
//     medidas_x: 53,
//     medidas_y: 53,
//   },
//   {
//     id: 4,
//     imagen: "003",
//     nombre: "Cañuelas",
//     descripcion: "Cerámicas de interior",
//     puntaje: 2,
//     paisDeOrigen: "Argentina",
//     precio: 1381.3200000000002,
//     tipo: "Piso",
//     medidas_x: 50,
//     medidas_y: 50,
//   },
//   {
//     id: 5,
//     imagen: "004",
//     nombre: "Alberdi",
//     descripcion: "Cerámicas de interior",
//     puntaje: 3,
//     paisDeOrigen: "Argentina",
//     precio: 1250.1000000000001,
//     tipo: "Piso",
//     medidas_x: 46,
//     medidas_y: 46,
//   },
// ];
// jest.mock("axios");

// describe("Llamadas correctas al 'back' (Home)", () => {

//   it("Traer los productos", async () => {
//     const resp = { data: JSON_HOME };
//     axios.get.mockResolvedValueOnce(resp);
//     const productos = await obtenerProductoFilter();
//     expect(axios.get).toHaveBeenCalledWith(
//       REST_SERVER_URL + `/productos/busqueda?`
//     );
//     expect(productos).toEqual(JSON_HOME);
//   });

//   it("Traer los productos que coincidan con la busqueda", async () => {
//     const { getAllByTestId } = render(<Home />);
//     const JSON_Egger = [
//         {
//             "id": 9,
//             "imagen": "008",
//             "nombre": "Egger",
//             "descripcion": "Colocación: Sistema Just Click. Uso recomendado: residencial",
//             "puntaje": 2,
//             "paisDeOrigen": "Alemania",
//             "precio": 1862.1000000000001,
//             "tipo": "Piso",
//             "medidas_x": 19.2,
//             "medidas_y": 129.2
//         }
//     ];
//     const resp = { data: JSON_Egger };
//     const searchBarInputs = getAllByTestId("searchBar");
//     const searchBarInput = searchBarInputs[0]
//     userEvent.type(searchBarInput, "Egger");
//     expect(searchBarInput.value).toBe("Egger");
//     const searchBar = searchBarInput.value;
//     axios.get.mockResolvedValue(resp);
//     const productos = await obtenerProductoFilter(searchBar);
//     expect(axios.get).toHaveBeenLastCalledWith(
//       REST_SERVER_URL + `/productos/busqueda?nombre=${searchBar}`
//     );
//     expect(productos).toEqual(JSON_Egger);
//   });

//   it("Traer los productos que coincidan con el filtro (puntaje)", async () => {
//     const { getByTestId } = render(<Home />);
//     const JSON_5Stars = [
//         {
//             "id": 7,
//             "imagen": "006",
//             "nombre": "Lume",
//             "descripcion": "Revestimiento para pared",
//             "puntaje": 5,
//             "paisDeOrigen": "Brasil",
//             "precio": 1340.1000000000001,
//             "tipo": "Piso",
//             "medidas_x": 33,
//             "medidas_y": 60
//         },
//         {
//             "id": 12,
//             "imagen": "011",
//             "nombre": "Z10",
//             "descripcion": "Supercubritivo Látex interior Mate",
//             "puntaje": 5,
//             "paisDeOrigen": "Argentina",
//             "volumen": 20,
//             "color": "#FFFFFF",
//             "precio": 1719.4049999999997,
//             "tipo": "Pintura"
//         },
//         {
//             "id": 17,
//             "imagen": "016",
//             "nombre": "Brikol",
//             "descripcion": "Impregnante para ladrillose",
//             "puntaje": 5,
//             "paisDeOrigen": "Argentina",
//             "volumen": 4,
//             "color": "#A27A65",
//             "precio": 5916.8025,
//             "tipo": "Pintura"
//         }
//     ];
//     const resp = { data: JSON_5Stars };
//     const checkBoxPuntaje = getByTestId("puntaje-5 puntos");
//     expect(checkBoxPuntaje.checked).toEqual(false);
//     fireEvent.click(checkBoxPuntaje);
//     expect(checkBoxPuntaje.checked).toEqual(true);
//     axios.get.mockResolvedValue(resp);
//     const productos = await obtenerProductoFilter("",5);
//     expect(axios.get).toHaveBeenLastCalledWith(
//       REST_SERVER_URL + `/productos/busqueda?nombre=&puntaje=5`
//     );
//     expect(productos).toEqual(JSON_5Stars);
//   });

//   it("Traer los productos que coincidan con el filtro (pais)", async () => {
//     const { getByTestId } = render(<Home />);
//     const JSON_Arg = [{
//       "id": 1,
//       "imagen": "099",
//       "nombre": "combo",
//       "descripcion": "pisos y pinturas",
//       "puntaje": 1,
//       "paisDeOrigen": "Argentina",
//       "precioBase": 4714.099999999999,
//       "precio": 4242.69,
//       "tipo": "Combo"
//   },
//   {
//       "id": 2,
//       "imagen": "001",
//       "nombre": "Cañuelas",
//       "descripcion": "Cerámica de interior",
//       "puntaje": 0,
//       "paisDeOrigen": "Argentina",
//       "precio": 1007.1,
//       "tipo": "Piso",
//       "medidas_x": 50,
//       "medidas_y": 50
//   },
//   {
//       "id": 3,
//       "imagen": "002",
//       "nombre": "Lourdes",
//       "descripcion": "Porcelanatos",
//       "puntaje": 1,
//       "paisDeOrigen": "Argentina",
//       "precio": 1813.3200000000002,
//       "tipo": "Piso",
//       "medidas_x": 53,
//       "medidas_y": 53
//   },
//     ];
//     const resp = { data: JSON_Arg };
//     const checkBoxPais = getByTestId("pais-Argentina");
//     expect(checkBoxPais.checked).toEqual(false);
//     fireEvent.click(checkBoxPais);
//     expect(checkBoxPais.checked).toEqual(true);
//     axios.get.mockResolvedValue(resp);
//     const productos = await obtenerProductoFilter("",undefined,"Argentina");
//     expect(axios.get).toHaveBeenLastCalledWith(
//       REST_SERVER_URL + `/productos/busqueda?nombre=&paisDeOrigen=Argentina`
//     );
//     expect(productos).toEqual(JSON_Arg);
//   });

//   it("Traer los productos que coincidan con los filtros y la busqueda", async () => {
//     const { getByTestId, getAllByTestId } = render(<Home />);
//     const JSON = [{
//       id: 5,
//       imagen: "004",
//       nombre: "Alberdi",
//       descripcion: "Cerámicas de interior",
//       puntaje: 3,
//       paisDeOrigen: "Argentina",
//       precio: 1250.1000000000001,
//       tipo: "Piso",
//       medidas_x: 46,
//       medidas_y: 46,
//     }
//     ];
//     const resp = { data: JSON };
//     const searchBarInputs = getAllByTestId("searchBar");
//     const searchBarInput = searchBarInputs[0]
//     userEvent.type(searchBarInput, "Alberdi");
//     const checkBoxPuntaje = getByTestId("puntaje-3 o más");
//     fireEvent.click(checkBoxPuntaje);
//     const checkBoxPais = getByTestId("pais-Argentina");
//     fireEvent.click(checkBoxPais);
//     axios.get.mockResolvedValue(resp);
//     const productos = await obtenerProductoFilter("Alberdi",3,"Argentina");
//     expect(axios.get).toHaveBeenLastCalledWith(
//       REST_SERVER_URL + `/productos/busqueda?nombre=Alberdi&paisDeOrigen=Argentina&puntaje=3`
//     );
//     expect(productos).toEqual(JSON);
//   });
// });
