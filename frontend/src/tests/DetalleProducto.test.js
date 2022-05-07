import axios from "axios";
import { REST_SERVER_URL } from "../REST_SERVER_URL";
import { obtenerProductoById } from "../services/productoService";

jest.mock("axios");
describe("Llamadas correctas al 'back' (DetallesProducto)", () => {
  it("Traer a Egger (producto id 9)", async () => {
    const JSON_Egger = [
      {
        id: 9,
        imagen: "008",
        nombre: "Egger",
        descripcion:
          "Colocación: Sistema Just Click. Uso recomendado: residencial",
        puntaje: 2,
        paisDeOrigen: "Alemania",
        precio: 1862.1000000000001,
        tipo: "Piso",
        medidas_x: 19.2,
        medidas_y: 129.2,
      },
    ];
    const resp = { data: JSON_Egger };
    axios.get.mockResolvedValueOnce(resp);
    obtenerProductoById(9).then((data) => expect(data).toEqual(JSON_Egger));
    expect(axios.get).toHaveBeenCalledWith(
      `${REST_SERVER_URL}/producto?productoId=9`
    );
  });
});
