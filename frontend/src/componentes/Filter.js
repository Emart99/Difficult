import { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { PaisHandler, PuntajeHandler } from "./utils/filterUtils";

export function Filter(setPuntaje, setPais, handleClose) {
  var nombresCheckboxPuntaje = [
    "5 puntos",
    "4 o más",
    "3 o más",
    "2 o más",
    "1 o más",
    "Todos",
  ];
  var nombresCheckboxPaisDeOrigen = [
    "Argentina",
    "Brasil",
    "Alemania",
    "China",
    "Todos",
  ];
  const [radioPuntaje, setRadioPuntaje] = useState();
  const [radioPais, setRadioPais] = useState();

  const formHandler = (event) => {
    event.preventDefault();
    setPuntaje(PuntajeHandler(radioPuntaje));
    setPais(PaisHandler(radioPais));
  };
  return (
    <div className="filter ">
      <h2 className="text-center">Filtros</h2>

      <Form
        id="myButton"
        onSubmit={(event) => formHandler(event)}
        className="d-flex flex-column"
      >
        <hr></hr>
        <h5 className="text-center">Puntaje</h5>
        {nombresCheckboxPuntaje.map((type, index) => (
          <div key={`inline-${type}`} id="checkboxColor" className="mb-3">
            <Form.Check
              label={type}
              name="radioPuntaje"
              type="radio"
              id={`puntaje-${type}-1`}
              onChange={() => setRadioPuntaje(index)}
              data-testid={`puntaje-${type}`}
            />
          </div>
        ))}
        <hr></hr>
        <h5 className="text-center">Origen</h5>
        {nombresCheckboxPaisDeOrigen.map((type, index) => (
          <div key={`inline-${type}`} id="checkboxColor" className="mb-3">
            <Form.Check
              className="radio"
              label={type}
              name="radioPais"
              type="radio"
              id={`pais-${type}-1`}
              onChange={() => setRadioPais(index)}
              data-testid={`pais-${type}`}
            />
          </div>
        ))}

        <Button
          className="botonFilter"
          type="submit"
          onClick={handleClose}
          data-testid="botonFilter"
        >
          Aplicar
        </Button>
      </Form>
    </div>
  );
}
