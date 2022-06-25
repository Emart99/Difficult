import { SearchBar } from "../SearchBar";
import "../../App.css";
import { Filter } from "./../Filter";
import { CardContainerContent } from "./../CardContainer";
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


  const carouselHandler = () => {
    if(productosMasVistos.length <= 0){
      return <br/>
    }
    return CarouselHome(productosMasVistos)

  }



  return (
    <div className="main content">
      {FilterMobile(setPuntaje, setPais)}
      {carouselHandler()}
      <div className="container-fluid mt-2">

        <div className="row">

          <div className="col-md-2 hide-filter px-3">

            {Filter(setPuntaje, setPais)}
          </div>
          <div className="col-12 col-md-12 col-lg-10" key="cardcontainer">
            {CardContainerContent(productos,textoABuscar, setTextoABuscar)}
          </div>
        </div>
      </div>
    </div>
  );
}
