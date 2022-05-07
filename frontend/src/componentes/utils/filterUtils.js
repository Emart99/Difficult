export function PuntajeHandler(valor) {
  let map = new Map();
  map.set(0, 5);
  map.set(1, 4);
  map.set(2, 3);
  map.set(3, 2);
  map.set(4, 1);
  map.set(5, null);
  return map.get(valor);
}

export function PaisHandler(valor) {
  let map = new Map();
  map.set(0, "Argentina");
  map.set(1, "Brasil");
  map.set(2, "Alemania");
  map.set(3, "China");
  map.set(4, null);
  return map.get(valor);
}
