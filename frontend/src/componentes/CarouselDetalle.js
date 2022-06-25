import { formatter } from "./utils/priceFormater";
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import { Link } from "react-router-dom";


export const CarouselDetalle = (productos) =>{
  return (
    <div className="container mt-5">
      <h3 className="text-center letra-dinamica mb-5">Quienes compraron este producto también compraron</h3>
    <Carousel 
    autoPlay={ true }
    autoPlaySpeed={5000}  
    infinite={true}
    itemClass="carousel-item-padding-40-px"
    responsive={responsive}>
      {productos.map((prod) => {
        return (
            <div
            id="mycard"
            className=""
            key={prod.id}
            data-testid="cardProducto"
          >
            <div className="card hover-effect " key={prod.id}>
              <a href={`/detalleDeProducto/${prod.id}`}>
                <img
                  loading="lazy"
                  src={"https://i.imgur.com/" + prod.imagen + ".png"}
                  className="card-img-top"
                  alt="..."
                />
              </a>

              <div className="card-body">
                <h5 className="card-title" data-testid="cardProductoNombre">
                  {prod.nombre}
                </h5>
                <p className="card-text">
                  Precio: {formatter.format(prod.precio)}
                </p>
                <p className="card-text">Descripcion: {prod.descripcion}</p>
              </div>
            </div>
          </div>
        );
      })}
    </Carousel>
    </div>
  )
}


export const responsive = {
  superLargeDesktop: {
    // the naming can be any, depends on you.
    breakpoint: { max: 4000, min: 3000 },
    items: 5
  },
  desktop: {
    breakpoint: { max: 3000, min: 1024 },
    items: 4
  },
  tablet: {
    breakpoint: { max: 1024, min: 575 },
    items: 2
  },
  mobile: {
    breakpoint: { max: 575, min: 0 },
    items: 1
  }
};