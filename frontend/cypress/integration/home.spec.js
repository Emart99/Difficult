describe("Casos del home", () => {
  beforeEach(() => {
    cy.visit("/");
  });

  it("Uso de searchBar", () => {
    const input = "Egger";
    cy.get("[data-testid=searchBar]").type(input);
    cy.get("[data-testid=cardProductoNombre]").then(($h5) => {
      expect($h5).to.have.text(input);
    });
  });

  it("Uso del filter puntaje", () => {
    const puntaje = "5";
    cy.get(`[data-testid^=puntaje-${puntaje}]`).click();
    cy.get("[data-testid=botonFilter]").click();
    cy.get("i[data-star]").each(($estrellita) => {
      cy.wrap($estrellita).should("have.attr", "data-star", puntaje);
    });
  });

  it("Uso del filter puntaje", () => {
    const pais = "Argentina";
    cy.get(`[data-testid=pais-${pais}]`).click();
    cy.get("[data-testid=botonFilter]").click();
    cy.get("p[data-testid=cardProductoPais]").each(($p) => {
      expect($p).to.have.text(`Origen: ${pais}`);
    });
  });

  it("Prueba full de la página", () => {
    const pais = "Argentina";
    const puntaje = "3";
    const input = "c";
    cy.get("[data-testid=searchBar]").type(input);
    cy.get(`[data-testid=pais-${pais}]`).click();
    cy.get(`[data-testid^=puntaje-${puntaje}]`).click();
    cy.get("[data-testid=botonFilter]").click();
    cy.get("div[data-testid=cardProducto]").then(($card) => {
      cy.wrap($card)
        .get("p[data-testid=cardProductoPais]")
        .each(($p) => {
          expect($p).to.have.text(`Origen: ${pais}`);
        });
      cy.wrap($card)
        .get("i[data-star]")
        .each(($estrellita) => {
          cy.wrap($estrellita)
            .invoke("attr", "data-star") // invoco al data-star
            .then(parseInt) // parseo el string a int
            .should("be.gte", +puntaje); //gte = greater than or equal
        });
    });
    cy.get("img").eq(1).click();
    cy.location().should((loc) => {
      expect(loc.pathname).to.not.eq("/home");
    });
  });
});
