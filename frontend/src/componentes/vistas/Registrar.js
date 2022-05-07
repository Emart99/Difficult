import Button from "react-bootstrap/Button";
import { Card, Form, InputGroup } from "react-bootstrap";
import { useState } from "react";
import DatePicker from "react-date-picker";
import { useHistory } from "react-router-dom";
import { MostrarError } from "../utils/toast";
import { crearUsuario, estaLogeado } from "../../services/usuarioService";
import { ToastContainer } from "react-toastify";
import swal from 'sweetalert';

export function Registrar() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirmation, setPasswordConfirmation] = useState("");
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [fechaNacimiento, setFechaNacimiento] = useState(new Date());
  const history = useHistory();

  if (estaLogeado()) {
    history.push("/home");
  }
  const handleVolverAlLogin = () => {
    history.push("/login");
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    if (password === passwordConfirmation && handleAge(fechaNacimiento) > 18) {
      await crearUsuario(
        nombre,
        apellido,
        username,
        password,
        handleAge(fechaNacimiento)
      );
      swal("Usuario creado con exito", "Saludos " + nombre, "success").then(()=>{
        handleVolverAlLogin();
      })
    } else {
      MostrarError("Ups algo ocurrio");
    }
  };
  const handleAge = (date) => {
    const edad = new Date().getFullYear() - date.getFullYear();
    return edad;
  };

  return (
    <section className="main">
      <ToastContainer limit="3"/>
      <div className="logo-fondo" id="myLogincard">
        <Card className="register-card transformed ">
          <h1 className=" cartel">Difficult</h1>

          <Card.Body className="p-3  white-body">
            <h5 className="text-end mt-4 mb-2">CREAR USUARIO</h5>

            <Form noValidate onSubmit={handleSubmit}>
              <Form.Label as="div">Usuario</Form.Label>
              <Form.Control
                className="mb-3"
                type="text"
                placeholder="Ingrese el usuario"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
                data-testid="userInput"
              />

              <Form.Label as="div">Contraseña</Form.Label>
              <Form.Control
                className="mb-3"
                type="password"
                placeholder="Ingrese su contraseña"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                data-testid="passwordInput"
              />

              <Form.Label as="div">Confirmar Contraseña</Form.Label>
              <Form.Control
                className="mb-3"
                type="password"
                placeholder="Repetir contraseña"
                value={passwordConfirmation}
                onChange={(e) => setPasswordConfirmation(e.target.value)}
                required
                data-testid="passwordInput2"
              />
              <Form.Label as="div">Nombre</Form.Label>
              <Form.Control
                className="mb-3"
                type="text"
                placeholder="Ingrese su nombre"
                value={nombre}
                onChange={(e) => setNombre(e.target.value)}
                required
                data-testid="userInput"
              />
              <Form.Label as="div">Apellido</Form.Label>
              <Form.Control
                className="mb-3"
                type="text"
                placeholder="Ingrese su apellido"
                value={apellido}
                onChange={(e) => setApellido(e.target.value)}
                required
                data-testid="userInput"
              />

              <Form.Label as="div">Fecha de nacimiento</Form.Label>
              <InputGroup className="mb-3 d-flex flex-column-reverse">
                <DatePicker
                  className="input-box"
                  onChange={setFechaNacimiento}
                  value={fechaNacimiento}
                />
              </InputGroup>

              <div className="d-flex justify-content-center" id="myButton">
                <Button type="submit" data-testid="botonInput">
                  Registrar
                </Button>
              </div>
              <div className="separador" />
              <p className="register-back-to-login text-center  mt-3 mb-0">
                Ya tenes una cuenta y te metiste porque sos medio salame?
                <br />
                <a href="/login" className=" register-back-to-login fw-bold ">
                  <u>Aca vas al login!</u>
                </a>
              </p>
            </Form>
          </Card.Body>
        </Card>
      </div>
    </section>
  );
}
