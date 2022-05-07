import { upperFirst } from "lodash";
import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Form, Row } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import { number2Fixed } from "../../componentes/utils/numberFixed";
import {
  estaLogeado,
  guardarUsuario, traerUsuario
} from "../../services/usuarioService";
import { tablaPerfil } from "../tablas/tablaPerfil";

export function Perfil() {
  const [user, setUser] = useState(false);
  const history = useHistory();
  useEffect(() => {
    if (estaLogeado()) {
      traerUsuario().then((data) => {
        setUser(data);
      });
    } else {
      history.push("/login");
    }
  }, [history]);

  
  const handleChanges = (e) => {
    e.preventDefault();
    if (user.edad > 0 && user.saldo > 0 && (user.edad % 1) === 0) {
      try {
        guardarUsuario(user);
      } catch (error) {
      }
    }
  };
  const imageHandler = () =>{
    if(user.imagen !== ' '){
      return <img className='foto-redondita' src={"https://i.imgur.com/"+user.imagen+'.png'} alt="..." />
    }
    else{
      return <img className='foto-redondita' src={'https://i.imgur.com/GGJhNbK.jpg'} alt="..." /> 
    }
    
  }

  const editar = (prop, valor) => {
    const userEditado = { ...user };
    userEditado[prop] = valor;
    setUser(userEditado);
  };

  const renderColumns = () => {
    let columns = [];
    columns.push(<Row key="RowText" className="my-4">
      {formPerfil("nombre", "text",false)}
      {formPerfil("apellido", "text",false)}
      </Row>)
    columns.push(<Row key="RowNumber" className="my-4">
      {formPerfil("edad", "number",true)}
      {formPerfil("saldo", "number",false)}
      </Row>)
    return columns;
  };

  function validar(prop) {
    if (prop === "nombre" || prop === "apellido") {
      return false;
    }
    return !(user[prop] > 0);
  }

  const formPerfil = (prop, type, bool) => {
    const label = upperFirst(prop);
    return (
      <Col key={"col " + label}>
        <Form.Label
          key={label + " label"}
          as="div"
          className="text-center"
        >
          {label}
        </Form.Label>
        <Form.Control
          required
          key={label}
          onChange={(e) => editar(prop, e.target.value)}
          type={type}
          value={number2Fixed(user[prop])}
          isInvalid={validar(prop)} //false = valido
          readOnly={bool}
          data-testid={`input-${prop}`}
        />
        <Form.Control.Feedback type="invalid">
          {label} debe ser mayor a 0.
        </Form.Control.Feedback>
      </Col>
    );
  };

  return (
    <Container>
    <div className="main" id="mycard">
    <Card className="mt-5 perfil">
      <Card.Header as="h3" className="text-center">
        Perfil de {user.nombre}
      </Card.Header>
      <Card.Body>
        <Card.Text as="div">
          <Container as="div">
            <Row xl={true} className="my-4">
              <Col
                md={true}
                style={{
                  margin: "auto",
                  display: "flex",
                  justifyContent: "center",
                }}
              >
                {imageHandler()}
              </Col>
             
              <Col xl={9}>
                <Form id="myButton" onSubmit={handleChanges}>
                  <Container as="div">
                    {user && renderColumns()}
                    <Button 
                      className="mt-4"
                      type="submit"
                      style={{
                        display: "block",
                        marginLeft: "auto",
                        marginRight: "2px",
                      }}
                      data-testid="botonGuardar"
                      size='lg'
                    >
                      Guardar
                    </Button>
                  </Container>
                </Form>
              </Col>
            </Row>

            <Row as="h3" className="mt-5">
              Compras realizadas
            </Row>
            <Row>
                {user && tablaPerfil(user)}
            </Row>
          </Container>
        </Card.Text>
      </Card.Body>
    </Card>
    </div>
    </Container>
  );
  
}



