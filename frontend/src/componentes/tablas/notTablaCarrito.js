import { BsFillTrashFill } from "react-icons/bs";
import { Container, Row, Col } from "react-bootstrap";
import { formatter } from "../utils/priceFormater";

export function notTablaCarrito(carrito, borrarHandler) {

  return (
    <>
      {carrito.map((item) => (
        <Container fluid className="carrito">
          <Row xl={12} className="p-0">
            <Col sm={3} className="px-0 align-self-center">
              <img
                className="img-carrito "
                src={"https://i.imgur.com/" + item.imagen + ".png"}
                alt=""
              ></img>
            </Col>
            <Col className="m-auto mt-4">
              <Row className="text-start">
                <h4>{item.nombre}</h4>
              </Row>
              <Row>
                <Col className="text-truncate" >
                  <i>{item.descripcion}</i>
                </Col>
                <Col sm={4} className="text-end">
                  <p>Lote: {item.loteId}</p>
                </Col>
              </Row>
              <Row>
                <Col>
                  <p>Cantidad: {item.cantidad}</p>
                </Col>
                <Col className="text-end text-nowrap">
                  <p>Precio: {formatter.format(item.precio)}</p>
                </Col>
              </Row>
            </Col>
            <Col sm={2} className="m-auto text-center">
              <button
                onClick={() => borrarHandler(item)}
                data-testid={`delete-${item.nombre}`}
                type="button"
                className="btn btn-link"
                style={{ padding: 0, color: "#fb293d", fontSize: "1.7rem" }}
              >
                <BsFillTrashFill />
              </button>
            </Col>
          </Row>
        </Container>
      ))}
    </>
  );
}
