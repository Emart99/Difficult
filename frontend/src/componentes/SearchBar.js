import { FormControl, InputGroup } from "react-bootstrap";
import { BsSearch } from "react-icons/bs";

export function SearchBar(textoABuscar, setTextoABuscar) {
  return (
    <div className="searchBar">
    <InputGroup >
    <InputGroup.Text className="iconSearchBar">
        <BsSearch />
    </InputGroup.Text>
      <FormControl
        style={{borderLeft:'0'}}
        className="input-searchbar"
        value={textoABuscar}
        onChange={(e) => setTextoABuscar(e.target.value)}
        required
        placeholder="Buscar"
        data-testid="searchBar"
      />
     
    </InputGroup>
    </div>
  );
}
