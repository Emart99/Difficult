import _ from "lodash";
import { useEffect, useState } from "react";
import { Button, Card, Container } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import {
  borrarDelCarrito, comprarCarrito, limpiarCarrito, traerCarrito
} from "../../services/carritoService";
import { estaLogeado } from "../../services/usuarioService";
import { tablaCarrito } from "../tablas/tablaCarrito";
import { ToastContainer } from 'react-toastify';
import { MostrarError } from "../utils/toast";
import swal from "sweetalert";

export function Carrito() {
  const [carrito, setCarrito] = useState([]);
  const [onClickRefresh, setOnClickRefresh] = useState(false);
  const history = useHistory();

  useEffect(() => {
    if (estaLogeado()) {
      traerCarrito().then((data) => {
        setCarrito(data);
      });
    } else {
      history.push("/login");
    }
  }, [onClickRefresh, history]);

  const borrarHandler = (item) => {
    carrito.splice(carrito.indexOf(item), 1);
    borrarDelCarrito(item.id);
    setOnClickRefresh(!onClickRefresh);
  };

  const limpiarCarritoHandler = () => {
    limpiarCarrito();
    setCarrito([]);
    setOnClickRefresh(!onClickRefresh);
  };

  const compradorHandler = async (event) => {
    if (carrito.length > 0) {
      await comprarCarrito().then(() =>{
        swal("La compra ha sido realizada correctamente","","success")
        setCarrito([]);
        setOnClickRefresh(!onClickRefresh);

      }).catch((e) => {
        MostrarError(e.response.data.mensaje)
      });

    } else {
      event.preventDefault();
    }
  };

  function carritoVacio() {
    if (!carrito.length) {
      return (
        <h5 className="text-center" data-testid="carritoVacio">
          No hay elementos en el carrito
        </h5>
      );
    }
  }

  return (
    <>
    <ToastContainer style={{width:'370px'}} limit="3"/>
      <Container id="mycard" className="carrito-container">
        <Card className="main">
          <Card.Header className="m-5" as="h1">
            Carrito de compras
          </Card.Header>

          <Card.Body as="div" className="carrito-card-body">
            {tablaCarrito(carrito, borrarHandler)}
            {carritoVacio()}
          </Card.Body>
          <Card.Footer id="myButton" as="div" className="text-end mx-2">
            <h3 className="mb-4 mx-2" data-testid="totalTxt">
              Total: $
              <span data-testid="totalCarrito">
                {_.sum(_.map(carrito, (i) => i.precio)).toFixed(2)}
              </span>
            </h3>
            <Button
              size="lg"
              variant="outline-light"
              className="mx-3"
              onClick={() => limpiarCarritoHandler()}
              data-testid="limpiarCarrito"
            >
              Limpiar
            </Button>
            <Button
              size="lg"
              onClick={(e) => {compradorHandler(e);return false}}
              data-testid="comprarCarrito"
            >
              Comprar
            </Button>
          </Card.Footer>
        </Card>
      </Container>
    </>
  );
}
