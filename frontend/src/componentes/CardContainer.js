import { Estrellitas } from "./estrellitas";
import { formatter } from "./utils/priceFormater";
import { SearchBar } from './SearchBar';

const productosHandler = (productos,textoABuscar, setTextoABuscar) => {
  if (productos.length <= 0) {
    return (
      <>
        <img
          className="not-productos"
          alt=""
          role="presentation"
          src="https://assets.market-storefront.envato-static.com/storefront/packs/media/images/search/not_found-c30e2e7521854866230a7626a306b9e5.png"
        />
        <div className="table">No se encontraron resultados</div>
      </>
    );
  }
  return CardContainer(productos);
};

const CardContainer = (productos) => {
  return (
    <>
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
    
  ))}</>)

}
const handlerProductosEncontrados = (productos) => {
  if (productos.length > 0) {
    return (
      <h6 className=" col-lg-12 text-start contador-productos mb-0 col-12 letra-dinamica text-nowrap">Productos encontrados: {productos.length}</h6>
    )
  }
}
export function CardContainerContent(productos,textoABuscar, setTextoABuscar) {
  return (
    <div className="container-fluid " data-testid="home-card-container">
      {SearchBar(textoABuscar,setTextoABuscar)}
      <div className="row g-3">
        {handlerProductosEncontrados(productos)}
        {productosHandler(productos,textoABuscar, setTextoABuscar)}
      </div>
    </div>
  );
}
