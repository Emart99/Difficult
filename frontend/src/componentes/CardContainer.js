import { Estrellitas } from "./estrellitas";
import { formatter } from "./utils/priceFormater";

export function CardContainer(productos) {
  return (
    <div className="container-fluid ">
      <div className="row g-3">
        {productos.map((producto) => (
          <div
            id="mycard"
            className="col-12 col-md-6 col-lg-4 col-xl-3"
            key={producto.id}
            data-testid="cardProducto"
          >
            <div className="card hover-effect " key={producto.id}>
              <a
                href={`detalleDeProducto/${producto.id}`}
                className="card-link"
              >
                <img
                  loading="lazy"
                  src={"https://i.imgur.com/" + producto.imagen + ".png"}
                  className="card-img-top"
                  alt="..."
                />
              </a>

              <div className="card-body">
                <h5 className="card-title" data-testid="cardProductoNombre">
                  {producto.nombre}
                </h5>
                <p className="card-text">{Estrellitas(producto.puntaje)}</p>
                <p className="card-text">
                  Precio: {formatter.format(producto.precio)}
                </p>
                <p className="card-text" data-testid="cardProductoPais">
                  Origen: {producto.paisDeOrigen}
                </p>
                <p className="card-text">Descripcion: {producto.descripcion}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
