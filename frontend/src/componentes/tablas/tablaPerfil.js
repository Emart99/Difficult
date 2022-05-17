import { Table } from "react-bootstrap";
import { formatter } from "../utils/priceFormater";

export function tablaPerfil(user) {
  const tablaHeader = [
    "Orden compra",
    "Cantidad artículos",
    "Importe total",
    "Fecha",
  ];
  const Columas = (list) => {
    let row = [];
    for (let i in list) {
      i === "importeTotal"
        ? row.push(
            <td data-testid={i} key={i}>
              {formatter.format(list[i])}
            </td>
          )
        : row.push(
            <td data-testid={i} key={i}>
              {list[i]}
            </td>
          );
    }
    return row;
  };

  const Body = (body) => {
    let bodyFinal = [];
    body.map((row) =>
      bodyFinal.push(<tr key={Math.random()}>{Columas(row)}</tr>)
    );
    return bodyFinal;
  };
  return (
    <Table bordered>
      <thead>
        <tr key="TableRow">
          {tablaHeader.map((label) => (
            <th key={label}>{label}</th>
          ))}
        </tr>
      </thead>
      <tbody data-testid="comprasUser">{Body(user.comprasRealizadas)}</tbody>
    </Table>
  );
}
