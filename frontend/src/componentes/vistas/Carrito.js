import _ from "lodash";
import { useEffect, useState } from "react";
import { Button, Container } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import {
  borrarDelCarrito, comprarCarrito, limpiarCarrito, traerCarrito
} from "../../services/carritoService";
import { estaLogeado } from "../../services/usuarioService";
import { ToastContainer } from 'react-toastify';
import { MostrarError } from "../utils/toast";
import swal from "sweetalert";
import { notTablaCarrito } from "../tablas/notTablaCarrito";
import { formatter } from "../utils/priceFormater";

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
        <h4 className="text-center mt-2" style={{minHeight:'47vh'}} data-testid="carritoVacio">
          Tu carrito está vacío
        </h4>
      );
    }
  }

  return (
    <>
    <ToastContainer style={{width:'370px'}} limit="3"/>
      <Container id="mycard" className="carrito-container">
        
          <h3 className="mb-4 letra-dinamica text-carrito" >
            Carrito de compras
          </h3>

          <div className="carrito-card-body">
            {notTablaCarrito(carrito, borrarHandler)}
            {carritoVacio()}

          <div id="myButton" as="div" className="text-end mx-2">
            <h4 className="mb-4 mx-2 letra-dinamica text-carrito" data-testid="totalTxt">
              Total: {" "}
              <span data-testid="totalCarrito">
                {formatter.format(_.sum(_.map(carrito, (i) => i.precio)))}
              </span>
            </h4>
            <div className="carrito-button-container">
              <Button
                size="lg"
                variant="outline-secondary"
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
            </div>
            </div>
          </div>
      </Container>
    </>
  );
}
