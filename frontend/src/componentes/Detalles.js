import { upperFirst } from "lodash";
import { firstUpper } from "../componentes/utils/stringUtils";
import { Combo } from "../dominio/Producto";

// Presentational Component
export function Detalles(producto){

    const listaHandler = (lista, _producto)  => {
      for (let atributo in _producto) {
        lista.push(
          <li key={Math.random()}>
            {upperFirst(atributo)}: {firstUpper(_producto[atributo])}
          </li>
        );
    }
    }
    const tipoHandler = (producto) => {
        let lista = [];
        if(producto.tipo instanceof  Combo){
          lista.push(<hr key="hola soy un hr" />)
          producto.tipo.productos.forEach(producto =>{
            listaHandler(lista,producto)
            lista.push(<hr key={Math.random()}/>)
          })
        }
        else{
          listaHandler(lista,producto.tipo)
        }
        return lista;
      };
      

      return (
        <div  className="card mt-5">
        <div className="card-body">
          <div className="row">
            <div className="col-md-12">
              <h3>Detalles de Producto</h3>
              <hr />
              <li>Origen : {producto.paisDeOrigen}</li>
              {tipoHandler(producto)}
            </div>
          </div>
        </div>
      </div>
      )
}