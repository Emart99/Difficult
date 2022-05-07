package aplicacion.dominio.producto

interface View {
    interface ProductoLista
    interface ProductoIndividual : ProductoLista
}