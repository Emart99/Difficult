import axios from "axios";
import { REST_SERVER_URL } from "../REST_SERVER_URL";
import { traerCarrito } from "../services/carritoService";

jest.mock("axios");

describe("Llamadas correctas al 'back' (Carrito)", () => {
  //CAMBIAR JSON
  it("Traer el carrito", async () => {
    const compras = [
      {
        ordenDeCompra: 7,
        cantidadDeArticulos: 2,
        importeTotal: 102371.625,
        fechaDeCompra: "2022-04-07",
      },
    ];
    // const history = {push: jest.fn()};
    // const { getByText } = render(<Carrito history={history}/>);
    Storage.prototype.getItem = jest.fn(() => "8");
    const resp = { data: compras };
    axios.get.mockResolvedValueOnce(resp);
    const carrito = await traerCarrito();
    expect(axios.get).toHaveBeenCalledWith(
      REST_SERVER_URL + `/carrito/items?id=8`
    );
    expect(carrito).toEqual(compras);
    // const noItems = getByText("No hay elementos en el carrito");
    // expect(noItems).toBeInTheDocument();
  });
});
