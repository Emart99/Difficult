import { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { logear, estaLogeado } from "../../services/usuarioService";
import { useHistory } from "react-router-dom";
import { ToastContainer } from 'react-toastify';
import { MostrarError } from "../utils/toast";

export function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [validated2, setValidated2] = useState(false);
  const validated = false;
  const history = useHistory();
  const [loginMessage, setLoginMessage] = useState([
    "Ingrese el usuario",
    "Ingrese la contraseña",
  ]);

  if (estaLogeado()) {
    history.push("/home");
  }
  
  const handleSubmit = async (event) => {
    const form = event.currentTarget;
    event.preventDefault();
    if (form.checkValidity() === false) {
      setLoginMessage(["Ingrese el usuario", "Ingrese la contraseña"]);
      setValidated2(true);
      event.stopPropagation();
    } else {
      setValidated2(false);
      logear(username, password)
        .then(() => {
          history.push("/home");
        })
        .catch((e) => {
          setValidated2(true);
          MostrarError(e.response.data.mensaje)
          setLoginMessage(["Usuario inválido", "Contraseña inválida"]);
        });
    }
  };

  return (
    <div className="main" id="myLogincard">
      <ToastContainer  data-testid="toast" limit="3"/>
      <div className="logo-fondo">
        <Card className="login-card  ">
          <h1 className="cartel">Difficult</h1>
          <Card.Body className="white-body">
            <Form noValidate validated={validated} onSubmit={handleSubmit}>
              <Form.Group className="mb-4" controlId="formBasic">
                <Form.Label as="div" className="text-center">
                  Usuario
                </Form.Label>

                <Form.Control
                  style={{ fontFamily: "Arial, FontAwesome" }}
                  placeholder="&#xf007;   Ingrese el usuario"
                  type="text"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                  data-testid="userInput"
                  isInvalid={validated2}
                />

                <Form.Control.Feedback
                  className="text-center"
                  type="invalid"
                  style={{ fontSize: "1.2rem" }}
                >
                  {loginMessage[0]}
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group className="mb-4" controlId="formBasicPassword">
                <Form.Label as="div" className="text-center">
                  Contraseña
                </Form.Label>

                <Form.Control
                  style={{ fontFamily: "Arial, FontAwesome" }}
                  type="password"
                  placeholder="&#xF023;   Ingrese la contraseña"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  data-testid="passwInput"
                  isInvalid={validated2}
                />

                <Form.Control.Feedback
                  className="text-center"
                  type="invalid"
                  style={{ fontSize: "1.2rem" }}
                >
                  {loginMessage[1]}
                </Form.Control.Feedback>
              </Form.Group>

              <div className="d-flex flex-row justify-content-evenly" id="myButton" >
                <Button variant="outline-light" type="submit" data-testid="botonInput">
                  Iniciar sesion
                </Button>
                <Button href="/registrar">
                  Crear cuenta
                </Button>
              </div>
              <div className="separador" />
              <p href="" className="forgotten">
                <b>Olvido su contraseña?</b>
              </p>
            </Form>
          </Card.Body>
        </Card>
      </div>
    </div>
  );
}
