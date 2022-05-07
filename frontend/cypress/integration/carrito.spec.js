describe("Casos del carrito", () => {
  const comprarProducto = (id, cantidad) => {
    cy.visit(`/detalleDeProducto/${id}`);
    cy.get("[data-testid^=producto]")
      .find("[data-testid^=radio-]")
      .last()
      .click();
    cy.get("[data-testid=cantidadInput]").clear().type(cantidad);
    cy.get("[data-testid=botonCarrito]").click();
  };

  beforeEach(() => {
    cy.visit("/login");
    cy.get("[data-testid=userInput]").type("Zeferina");
    cy.get("[data-testid=passwInput]").type("1234");
    cy.get("[data-testid=botonInput]").click();
    cy.wait(1000).then(() => {
      comprarProducto(9, 40);
      comprarProducto(2, 2);
    });
    cy.visit("/carrito");
  });

  it("Eliminar un elemento del carrito", () => {
    cy.get("[data-testid='tablaCarrito']")
      .find("tr")
      .first()
      .then(($carrito) => {
        const nLote = $carrito.find(`[data-testid^=carrito-N]`).text();
        cy.wrap($carrito)
          .find(`[data-testid^=delete-]`)
          .click()
          .then(() => {
            cy.get("[data-testid='tablaCarrito']")
              .find(`[data-testid=carrito-${nLote}]`)
              .should("not.exist");
          });
      });
  });

  it("Vaciar el carrito", () => {
    cy.get("[data-testid=limpiarCarrito]").click();
    cy.get("[data-testid=tablaCarrito]").then(($tbody) => {
      // cy.wait(1000);
      // expect($tbody).to.be.empty;
      cy.wrap($tbody).should("have.value", "");
    });
  });

  it("Finalizar compra exitosamente", () => {
    cy.wait(3000);
    let total;
    cy.get("[data-testid=totalCarrito]").then((tot) => {
      total = tot.text();
      cy.log(total);
      cy.get("[data-testid=comprarCarrito]").click();
      cy.visit("/perfil");
      cy.wait(1000).then(() => {
        cy.get("[data-testid=comprasUser]")
          .find("td[data-testid=importeTotal]")
          .then(($compra) => {
            expect($compra.first().text()).to.be.equal(total);
          });
      });
    });
  });
});
