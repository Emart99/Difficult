import "../componentes/estrellitas.css";

export function Estrellitas(valor) {
  return <i data-star={valor.toString()} data-testid="estrellita"></i>;
}
