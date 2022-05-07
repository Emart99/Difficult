import { BsFillTrashFill } from "react-icons/bs";
export function tablaCarrito(carrito, borrarHandler) {
  const tablaHeader = [
    "Producto",
    "Descripción",
    "Lote",
    "Cantidad",
    "Precio",
    "",
  ];

  function tBody(carrito, borrarHandler) {
    return (
      <tbody data-testid="tablaCarrito">
        {carrito.map((item) => (
          <tr data-testid="carrito" key={Math.random()}>
            <td data-testid={`carrito-${item.nombre}`}>{item.nombre}</td>
            <td>{item.descripcion}</td>
            <td data-testid={`carrito-N${item.loteId}`}>{item.loteId}</td>
            <td>{item.cantidad}</td>
            <td>{item.precio.toFixed(2)}</td>
            <td>
              <button
                onClick={() => borrarHandler(item)}
                data-testid={`delete-${item.nombre}`}
                type="button"
                className="btn btn-link"
                style={{ padding: 0, color: "#fb293d" }}
              >
                <BsFillTrashFill />
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    );
  }

  function tHeader() {
    return (
      <thead>
        <tr>
          {tablaHeader.map((text) => (
            <th key={text} scope="col">
              {text}
            </th>
          ))}
        </tr>
      </thead>
    );
  }

  return (
    <table className="table table-bordered text-center">
      {tHeader(carrito)}
      {tBody(carrito, borrarHandler)}
    </table>
  );
}
