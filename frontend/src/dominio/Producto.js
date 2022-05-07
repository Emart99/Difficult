export class Producto {
  constructor(
    _id = 0,
    _img,
    _nombre = "",
    _descripcion = "",
    _puntaje = 0,
    _paisDeOrigen = "",
    _lote,
    _precio = 0.0,
    _type
  ) {
    this.id = _id;
    this.imagen = _img;
    this.nombre = _nombre;
    this.descripcion = _descripcion;
    this.puntaje = _puntaje;
    this.paisDeOrigen = _paisDeOrigen;
    this.lotes = _lote;
    this.precio = _precio;
    this.tipo = _type;
  }
}

export class Pintura {
  constructor(_volumen = "0.0", _rendimiento = "0.0", _color = "") {
    this.volumen = _volumen;
    this.rendimiento = _rendimiento;
    this.color = _color;
  }
}

export class Piso {
  constructor(_medidasX, _medidasY, _transito, _terminacion) {
    this.medidasX = _medidasX;
    this.medidasY = _medidasY;
    this.transito = _transito;
    this.terminacion = _terminacion;
  }
}

export class Combo {
  constructor(_productos = []) {
    this.productos = _productos;
  }
}

export function ProductofromJSON(productoJSON) {
  return new Producto(
    productoJSON.id,
    productoJSON.imagen,
    productoJSON.nombre,
    productoJSON.descripcion,
    productoJSON.puntaje,
    productoJSON.paisDeOrigen,
    productoJSON.lotes,
    productoJSON.precio,
    tipo(productoJSON)
  );
}

function tipo(productoJSON) {
  if (productoJSON.tipo === "Pintura") {
    const tipo = new Pintura(
      productoJSON.volumen,
      productoJSON.rendimiento,
      productoJSON.color
    );
    return tipo;
  } else if (productoJSON.tipo === "Piso") {
    const tipo = new Piso(
      productoJSON.medidas_x,
      productoJSON.medidas_y,
      productoJSON.transito,
      productoJSON.terminacion
    );
    return tipo;
  } else {
    const tipo = new Combo(productoJSON.items);
    return tipo;
  }
}
