import { FormControl, InputGroup } from "react-bootstrap";
import { BsSearch } from "react-icons/bs";

export function SearchBar(textoABuscar, setTextoABuscar) {
  return (
    <div className="searchBar">
    <InputGroup >
      <FormControl
        className="input-searchbar"
        value={textoABuscar}
        onChange={(e) => setTextoABuscar(e.target.value)}
        required
        placeholder="Buscar"
        data-testid="searchBar"
      />
      <InputGroup.Text className="iconSearchBar">
        <BsSearch />
      </InputGroup.Text>
    </InputGroup>
    </div>
  );
}
