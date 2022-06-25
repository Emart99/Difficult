describe("Entrar al perfil deslogueado", () => {
  it("Se redirige al login", () => {
    cy.visit("/perfil");
    cy.location().should((loc) => {
      expect(loc.pathname).to.eq("/login");
    });
  });
});

describe("Casos del perfil", () => {
  beforeEach(() => {
    cy.visit("/login");
    cy.get("[data-testid=userInput]").type("Zeferina");
    cy.get("[data-testid=passwInput]").type("1234");
    cy.get("[data-testid=botonInput]").click();
    cy.wait(1000).then(() => {
      cy.visit("/perfil");
    });
  });

  it("Validación no deja guardar saldo negativo", () => {
    cy.get("[data-testid=input-saldo]").clear().type(-99);
    cy.get(".invalid-feedback").should("be.visible");
  });

  it("Cambio de saldo, nombre, apellido", () => {
    cy.get("[data-testid=input-saldo]").clear().type(3000000);
    cy.get("[data-testid=input-nombre]").clear().type("Zefe");
    cy.get("[data-testid=input-apellido]").clear().type("Chavez");
    cy.get("[data-testid=botonGuardar]").click();
    cy.reload();
    cy.get("[data-testid=input-saldo]").should("have.value", 3000000);
    cy.get("[data-testid=input-nombre]").should("have.value", "Zefe");
    cy.get("[data-testid=input-apellido]").should("have.value", "Chavez");
  });

  it("Botón/popover producto más visto", ()=>{    
    cy.visit("/perfil").then(()=>{
      cy.get("div .button-popover").find("button").click().then(()=>{
        cy.get("div[id='popover-basic']").find("h5").should('be.visible')
      })
    })
  })
});
