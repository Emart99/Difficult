import { fireEvent, render } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import axios from "axios";
import React from "react";
import { Login } from "../componentes/vistas/Login";
import { REST_SERVER_URL } from "../REST_SERVER_URL";
import { logear } from "../services/usuarioService";

jest.mock("axios");

describe("Llamadas correctas al 'back' (Login)", () => {
  let usernameInput;
  let passwordInput;
  it("LogIn con usuario/passw correcto", async () => {
    const { getByTestId } = render(<Login />);
    const spy = jest.spyOn(Storage.prototype, "setItem");
    usernameInput = getByTestId("userInput");
    passwordInput = getByTestId("passwInput");
    userEvent.type(usernameInput, "Zeferina");
    userEvent.type(passwordInput, "1234");
    fireEvent.click(getByTestId("botonInput"));
    const username = usernameInput.value;
    const password = passwordInput.value;
    expect(username).toBe("Zeferina");
    expect(password).toBe("1234");
    axios.put.mockResolvedValue();
    logear(username, password).then(() => {
      expect(spy).toHaveBeenCalledTimes(1);
    });
    expect(axios.put).toHaveBeenCalledWith(
      `${REST_SERVER_URL}/usuario?usuario=${username}&contrasenia=${password}`
    );
  });

  it("LogIn con passw incorrecta -> error", async () => {
    const username = "Zeferina";
    const password = "1224";
    const spy = jest.spyOn(Storage.prototype, "setItem");
    axios.put.mockRejectedValueOnce(new Error("Usuario o contraseña invalida"));
    logear(username, password).then(() => {
      expect(spy).toHaveBeenCalledTimes(0);
    });
    expect(axios.put).toHaveBeenCalledWith(
      `${REST_SERVER_URL}/usuario?usuario=${username}&contrasenia=${password}`
    );
  });
});
