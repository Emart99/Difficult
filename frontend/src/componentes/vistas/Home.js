import { SearchBar } from "../SearchBar";
import "../../App.css";
import { Filter } from "./../Filter";
import { CardContainer } from "./../CardContainer";
import { useEffect, useState } from "react";
import { obtenerProductoFilter } from "../../services/productoService";
import { FilterMobile } from "./../FilterMobile";
import { CarouselHome } from './../CarouselHome';
import { productosMasVisitados } from "../../services/clickLogService";
export function Home() {
  const [productos, setProductos] = useState([]);
  const [textoABuscar, setTextoABuscar] = useState("");
  const [puntaje, setPuntaje] = useState();
  const [pais, setPais] = useState();
  const [productosMasVistos,setProductosMasVistos] = useState([]);

  useEffect(() => {
    obtenerProductoFilter(textoABuscar, puntaje, pais).then((data) =>
      setProductos(data)
    );
    productosMasVisitados().then((data)=>setProductosMasVistos(data))
  }, [textoABuscar, puntaje, pais]);

  const productosHandler = () => {
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
  const carouselHandler = () => {
    if(productosMasVistos.length <= 0){
      return <br/>
    }
    return CarouselHome(productosMasVistos)

  }

  const handlerProductosEncontrados = () => {
    if (productos.length > 0) {
      return (
        <h6 className="px-3 col-lg-2 text-center col-12 letra-dinamica text-nowrap">Productos encontrados: {productos.length}</h6>
      )
    }
  }

  return (
    <div className="main content">
      {SearchBar(textoABuscar, setTextoABuscar)}
      {FilterMobile(setPuntaje, setPais)}
      {carouselHandler()}
      {handlerProductosEncontrados()}
      <div className="container-fluid">

        <div className="row">

          <div className="col-md-2 hide-filter px-3">

            {Filter(setPuntaje, setPais)}
          </div>
          <div className="col-12 col-md-12 col-lg-10" key="cardcontainer">
            {productosHandler()}
          </div>
        </div>
      </div>
    </div>
  );
}
