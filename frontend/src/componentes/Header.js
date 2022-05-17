import { Container, Nav, Navbar } from "react-bootstrap";
import { AiOutlineShoppingCart, AiOutlineUser } from "react-icons/ai";
import { FiLogOut } from "react-icons/fi";
import { estaLogeado, logout } from "./../services/usuarioService";
import "./switch.css";
const ButtonHandler = () => {
  if (estaLogeado()) {
    return [
      <Nav.Link key="1" href="/perfil">
        <AiOutlineUser />
        Mi Perfil
      </Nav.Link>,
      <Nav.Link key="2" onClick={logout} href="/login">
        <FiLogOut />
      </Nav.Link>,
    ];
  } else {
    return (
      <Nav.Link key="3" href="/login">
        <AiOutlineUser />
        Ingresar
      </Nav.Link>
    );
  }
};

export function Header(setTheme, theme) {
  const checkboxHandler = () => {
    return theme === "dark";
  };

  return (
    <Navbar expand="lg">
      <Container fluid>
        <img
          src="https://i.imgur.com/Twx16wi.png"
          alt="loguito"
          className="loguito-header"
        />
        <Nav.Item as="h1" className="title-header">
          Difficult
        </Nav.Item>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse className="navbar-collapse" id="basic-navbar-nav">
          <Nav id="myNavBar" className="hamburguesa">
            <div className="toggle-theme-wrapper">
              <span>☀️</span>
              <label className="toggle-theme" htmlFor="checkbox">
                <input
                  onClick={setTheme}
                  type="checkbox"
                  id="checkbox"
                  defaultChecked={checkboxHandler()}
                />
                <div className="slider round"></div>
              </label>
              <span>🌒</span>
            </div>
            <Nav.Link href="/home">
              Home
            </Nav.Link>
            <Nav.Link href="/carrito">
              <AiOutlineShoppingCart />
              Carrito
            </Nav.Link>
            <ButtonHandler />
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
