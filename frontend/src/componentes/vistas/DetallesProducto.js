import { Button, Alert, Form } from "react-bootstrap";
import { Estrellitas } from "./../estrellitas";
import { useEffect, useState } from "react";
import { obetenerRecomendaciones, obtenerProductoById } from "../../services/productoService";
import { ProductofromJSON } from "../../dominio/Producto";
import { useParams } from "react-router-dom";
import { agregarAlCarrito } from "../../services/carritoService";
import { estaLogeado } from "../../services/usuarioService";
import { tablaDetalleProducto } from "./../tablas/tablaDetalleProducto";
import { Detalles } from "../Detalles";
import { MostrarAgregado } from "../utils/toast";
import { ToastContainer } from "react-toastify";
import { formatter } from "../utils/priceFormater";
import { useHistory } from "react-router-dom";
import { logClickProducto } from "../../services/clickLogService";
import { CarouselDetalle } from "../CarouselDetalle";

export function DetallesProducto() {
  const { id } = useParams();
  const [producto, setProducto] = useState(false);
  const [cantidad, setCantidad] = useState(1);
  const [productosRecomendados, setProductosRecomendados] = useState([]);
  const [lote, setLote] = useState(undefined);
  const history = useHistory();

  useEffect(() => {
    async function fetchProducto() {
      console.log("id: "+id)
      const prod = await obtenerProductoById(id);
      await logClickProducto(prod)
      setProducto(ProductofromJSON(prod));
      const prods = await obetenerRecomendaciones(id);
      setProductosRecomendados(prods);
    }
    fetchProducto().catch(()=>history.push('/notFound'))
    
   
  }, [id,history]);

  function cantidadInvalida() {
    return lote && cantidad > lote.cantidadDeUnidades;
  }

  function cantidadPositiva(){
    return cantidad > 0 
  }

  function notLoggedIn() {
    return !estaLogeado();
  }

  function loteNoSeleccionado() {
    return !lote && !notLoggedIn();
  }

  function validar() {
    return !cantidadInvalida() && !loteNoSeleccionado() && !notLoggedIn() && cantidadPositiva();
  }

const handleCarrito = async () => {
    if (validar()) {
      try {
        await agregarAlCarrito(producto.id, lote.id, cantidad).then(()=>{
          MostrarAgregado("Se ha agregado correctamente al carrito")
        })
      } catch (e) {}
    }
};

  return (
    <section className="main content padding-y bg mt-5">
      <div id="mycard" className="container">
        <div className="card">
          <div className="card-body">
            <div className="row">
              <div className="col-md-6">
                <div className="nombre-imagen">
                  <h3 data-testid="nombreProducto" className="title">
                    {producto.nombre}
                  </h3>
                  <hr />
                  <h6>{producto.descripcion}</h6>
                  <div>{Estrellitas(producto && producto.puntaje)}</div>
                  <div className="card">
                    <img
                      src={"https://i.imgur.com/" + producto.imagen + ".png"}
                      className="img-detalle-producto"
                      alt="..."
                    />
                  </div>
                </div>
              </div>
              <main className="col-md-6">
                <div>
                  {tablaDetalleProducto(producto, setLote)}
                  <br />

                  <div
                    id="myButton"
                    className="d-flex flex-wrap flex-column align-self-md-end mb-4  "
                  >
                    <ToastContainer  limit={3} position="bottom-center" className="toast-producto "/>
                    <div className="mb-2">
                      <h3>{producto && formatter.format(producto.precio*cantidad)}</h3>
                    </div>
                    <Form.Label key="Cantidad" as="div">
                      Cantidad
                    </Form.Label>
                    <Form.Control
                      required
                      size="15"
                      key="CantidadInput"
                      onChange={(e) => setCantidad(e.target.value)}
                      type="number"
                      value={cantidad}
                      isInvalid={cantidadInvalida()} //false = valido
                      className="mb-2"
                      data-testid="cantidadInput"
                    />
                    <Form.Control.Feedback type="invalid">
                      La cantidad no puede ser mayor al stock disponible.
                    </Form.Control.Feedback>
                    <Button
                      size="sm"
                      onClick={() => handleCarrito()}
                      className="my-2"
                      data-testid="botonCarrito"
                    >
                      Agregar al carrito
                    </Button>
                    <Alert
                      key="AlertaSesion"
                      variant="danger"
                      show={notLoggedIn()}
                    >
                      Para agregar al carrito debe{" "}
                      <a href="/login">iniciar sesión</a>.
                    </Alert>
                    <Alert
                      key="AlertaLote"
                      variant="danger"
                      show={loteNoSeleccionado()}
                    >
                      Debe seleccionar primero el lote.
                    </Alert>
                    <Alert
                      key="AlertaCantidad"
                      variant="danger"
                      show={!cantidadPositiva() && !loteNoSeleccionado()}
                    >
                      La cantidad debe ser mayor a 0.
                    </Alert>
                  </div>
                </div>
              </main>
            </div>
          </div>
        </div>
        {Detalles(producto)}
      </div>
      <div>
      </div>
      {CarouselDetalle(productosRecomendados)}
    </section>
  );
}
