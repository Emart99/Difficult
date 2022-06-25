import { useEffect, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import { Filter } from "./Filter";

export function FilterMobile(setPuntaje, setPais) {
  const [fullscreen, setFullscreen] = useState(true);
  const [show, setShow] = useState(false);
  const [theme, setTheme] = useState("");
  const [onClickRefresh, setOnClickRefresh] = useState(false);

  useEffect(() => {
    const newTheme = JSON.parse(window.localStorage.getItem("theme"));
    setTheme(newTheme);
  }, [onClickRefresh]);

  function handleShow(breakpoint) {
    setFullscreen(breakpoint);
    setShow(true);
    setOnClickRefresh(!onClickRefresh);
  }
  const handleClose = () => setShow(false);

  return (
    <div className="filter-mobile">
      <Button
        className="filter-button"
        key={"filter"}
        onClick={() => handleShow(true)}
      >
        Filtrar
      </Button>
      <Modal
        id="myModal"
        show={show}
        fullscreen={fullscreen}
        onHide={() => setShow(false)}
        data-theme={theme}
      >
        <Modal.Header closeButton className="modal-header"></Modal.Header>
        <Modal.Body>{Filter(setPuntaje, setPais, handleClose)}</Modal.Body>
      </Modal>
    </div>
  );
}
