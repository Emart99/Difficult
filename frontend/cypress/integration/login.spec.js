describe("Casos de login", () => {
  beforeEach(() => {
    cy.visit("/login");
    cy.get("[data-testid=userInput]").type("Zeferina");
  });

  it("Login correcto", () => {
    cy.get("[data-testid=passwInput]").type("1234");
    cy.get("[data-testid=botonInput]").click();
    cy.wait(1000).then(() => {
      expect(localStorage.getItem("user_id")).to.eq("8");
    });
  });

  it("Login incorrecto (contraseña erronea)", () => {
    cy.get("[data-testid=passwInput]").type("1111");
    cy.get("[data-testid=botonInput]").click();
    cy.wait(1000).then(() => {
      expect(localStorage.getItem("user_id")).to.eq(null);
    });
    cy.get("[data-testid=toast]").then((toast) => {
      expect(toast).to.have.text("Usuario inexistente");
    });
  });

  it("Login sin datos -> aviso", () => {
    cy.get("[data-testid=userInput]").clear();
    cy.get("[data-testid=botonInput]").click();
    cy.wait(1000).then(() => {
      expect(localStorage.getItem("user_id")).to.eq(null);
      cy.get(".invalid-feedback").each(($feedback) => {
        cy.wrap($feedback).should("be.visible");
      });
    });
  });
});
