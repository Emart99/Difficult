describe("Casos del detalle de producto", () => {
  const eggerJSON = {
    id: 8,
    imagen: "008",
    nombre: "Egger",
    descripcion: "Colocación: Sistema Just Click. Uso recomendado: residencial",
    puntaje: 2,
    paisDeOrigen: "Alemania",
    lotes: [
      {
        id: 9893,
        fechaIngreso: "2021-11-30",
        cantidadDeUnidades: 16,
      },
      {
        id: 9055,
        fechaIngreso: "2021-12-16",
        cantidadDeUnidades: 106,
      },
      {
        id: 7451,
        fechaIngreso: "2022-01-01",
        cantidadDeUnidades: 166,
      },
      {
        id: 5215,
        fechaIngreso: "2022-01-17",
        cantidadDeUnidades: 213,
      },
      {
        id: 2538,
        fechaIngreso: "2022-02-02",
        cantidadDeUnidades: 241,
      },
      {
        id: 353,
        fechaIngreso: "2022-02-18",
        cantidadDeUnidades: 249,
      },
      {
        id: 3216,
        fechaIngreso: "2022-03-06",
        cantidadDeUnidades: 236,
      },
      {
        id: 5806,
        fechaIngreso: "2022-03-22",
        cantidadDeUnidades: 203,
      },
      {
        id: 7904,
        fechaIngreso: "2022-04-07",
        cantidadDeUnidades: 153,
      },
      {
        id: 9333,
        fechaIngreso: "2022-04-23",
        cantidadDeUnidades: 89,
      },
    ],
    transito: "NORMAL",
    terminacion: "SEMI_SATINADO",
    precio: 1862.1000000000001,
    tipo: "Piso",
    medidas_x: 19.2,
    medidas_y: 129.2,
  };

  beforeEach(() => {
    cy.visit("/");
    cy.get("[data-testid=searchBar]").type("egger");
    cy.get("[data-testid=home-card-container]").find("img").first().click({force: true});
    cy.get("div .alert a[href='/login']").click();
    cy.get("[data-testid=userInput]").type("Zeferina");
    cy.get("[data-testid=passwInput]").type("1234");
    cy.get("[data-testid=botonInput]").click();
    cy.wait(1000).then(() => {
      cy.get("[data-testid=searchBar]").type("egger");
      cy.get("[data-testid=home-card-container]").find("img").first().click({force: true});
    });
  });

  it("Se loguea y agrega un item correctamente", () => {
    cy.get("h3[data-testid=nombreProducto]").should(
      "have.text",
      eggerJSON.nombre
    );
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
      });
    cy.get("[data-testid=botonCarrito]").click();

    cy.visit("/carrito").then(() => {
      cy.get("div .carrito")
        .find("h4")
        .should("have.text", eggerJSON.nombre);
    });
  });

  it("Se loguea y agrega un item con cantidad mayor a la disponible", () => {
    cy.get("[data-testid^=producto]")
      .find("[data-testid^=radio-]")
      .first()
      .click();
    cy.get("[data-testid^=producto]")
      .find("[data-testid^=cant-]")
      .first()
      .invoke("text")
      .then((val) => {
        cy.get("[data-testid=cantidadInput]")
          .clear()
          .type(val + 1);
      });
    cy.get(".invalid-feedback")
      .should("be.visible")
      .and("have.text", "La cantidad no puede ser mayor al stock disponible.");
  });
});
