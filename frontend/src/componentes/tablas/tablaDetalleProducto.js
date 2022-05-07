export function tablaDetalleProducto(producto, setLote) {
  return (
    <div className="table-scroll-y scrollbar">
      <table className="table table-bordered " data-testid="tablilla">
        <thead>
          <tr>
            <th scope="col"></th>
            <th scope="col">Lote</th>
            <th scope="col">Fecha</th>
            <th scope="col">Cantidad</th>
          </tr>
        </thead>
        <tbody>
          {producto &&
            producto.lotes.map((lote) => (
              <tr
                id="checkboxColor"
                key={lote.id}
                data-testid={`producto-${lote.id}`}
              >
                <td>
                  <input
                    name="cosa"
                    value={lote.id}
                    type="radio"
                    checked={lote.selected}
                    className="form-check-input"
                    onChange={() => setLote(lote)}
                    data-testid={`radio-${lote.id}`}
                  />
                </td>
                <td data-testid={`lote-${lote.id}`}>{lote.id}</td>
                <td data-testid={`fecha-${lote.id}`}>{lote.fechaIngreso}</td>
                <td data-testid={`cant-${lote.id}`}>
                  {lote.cantidadDeUnidades}
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
}
