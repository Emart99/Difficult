describe("Casos del carrito", () => {

  const comprarProducto = () => {
    cy.visit("/");
    cy.get("[data-testid=searchBar]").type("Satinol");
    cy.get("[data-testid=home-card-container]").find("img").first().click();
    cy.get("[data-testid^=producto]")
      .find("[data-testid^=radio-]")
      .first()
      .click();
    cy.get("[data-testid^=producto]")
      .find("[data-testid^=cant-]")
      .first()
      .invoke("text")
      .then((val) => {
        cy.get("[data-testid=cantidadInput]").clear().type(val);
        cy.get("[data-testid=botonCarrito]").click();
      });
  };

  beforeEach(() => {
    cy.visit("/login");
    cy.get("[data-testid=userInput]").type("Zeferina");
    cy.get("[data-testid=passwInput]").type("1234");
    cy.get("[data-testid=botonInput]").click();
    cy.wait(1000).then(() => {
      comprarProducto();
    });
    cy.visit("/carrito");
  });

  it("Eliminar un elemento del carrito", () => {
    cy.get("div .carrito").then(($carrito) => {
      cy.wrap($carrito.find("h4"))
        .should("have.text", "Satinol")
        .then(() => {
          $carrito.find(`[data-testid^=delete-]`).click();
        });
    });
    cy.get("div .carrito").should("not.exist");
  });

  it("Vaciar el carrito", () => {
    cy.get("[data-testid=limpiarCarrito]").click();
    cy.get("div .carrito-vacio").should("exist");
  });

  it("Finalizar compra exitosamente", () => {
    cy.wait(1000);
    let total;
    cy.get("[data-testid=totalCarrito]").then((tot) => {
      total = tot.text();
      cy.log(total);
      cy.get("[data-testid=comprarCarrito]").click();
      cy.visit("/perfil");
      cy.wait(3000).then(() => {
        cy.get("[data-testid=comprasUser]")
          .find("td[data-testid=importeTotal]")
          .then(($compra) => {
            expect($compra.first().text()).to.be.equal(total);
          });
      });
    });
  });
});
