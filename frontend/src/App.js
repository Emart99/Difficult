import "./App.css";
import {
  BrowserRouter as Router,
  Route,
  Switch,
  Redirect,
} from "react-router-dom";
import { Header } from "./componentes/Header";
import { Footer } from "./componentes/Footer";
import { Home } from "./componentes/vistas/Home";
import { DetallesProducto } from "./componentes/vistas/DetallesProducto";
import { Login } from "./componentes/vistas/Login";
import { Perfil } from "./componentes/vistas/Perfil";
import { Carrito } from "./componentes/vistas/Carrito";
import { Registrar } from "./componentes/vistas/Registrar";
import useLocalStorage from "use-local-storage";
import { useEffect } from "react";

const HeaderHandler = (setTheme, theme) => {
  return (
    <Route
      render={({ location }) =>
        location.pathname === "/login" || location.pathname === "/registrar"
          ? null
          : Header(setTheme, theme)
      }
    />
  );
};

function App() {
  const defaultDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
  const [theme, setTheme] = useLocalStorage(
    "theme",
    defaultDark ? "dark" : "light"
  );

  const switchTheme = () => {
    const newTheme = theme === "light" ? "dark" : "light";
    setTheme(newTheme);
  };

  useEffect(() => {
    if (theme === "dark") {
      document.body.classList.add("dark");
    } else {
      document.body.classList.remove("dark");
    }
  }, [theme]);

  return (
    <div data-theme={theme}>
      <Router>
        {HeaderHandler(switchTheme, theme)}
        <Switch>
          <Route path="/home" component={Home} />
          <Route path="/detalleDeProducto/:id" component={DetallesProducto} />
          <Route path="/login" component={Login} />
          <Route path="/registrar" component={Registrar} />
          <Route path="/perfil" component={Perfil} />
          <Route path="/carrito" component={Carrito} />
          <Redirect from="*" to="/home" />
        </Switch>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
