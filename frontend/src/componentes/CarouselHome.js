import { Carousel } from "react-bootstrap"


export const CarouselHome = (productos) => {
    return (
        <Carousel className="mb-4 container-fluid pt-3" style={{width:'90%'}}>
            {productos && productos.map((producto)=>(
                
                    <Carousel.Item key={producto.idProducto}>
                    <a href={"/detalleDeProducto/"+producto.idProducto}>
                    <img
                        className="d-block carousel-img"
                        src={"https://i.imgur.com/" + producto.productoImagen + ".png"}
                        alt="First slide"
                    
                    />
                    </a>
                    <Carousel.Caption >
                        <h3 style={{textShadow:'2px 2px 3px black'}}>{producto.productoNombre}</h3>
                        <p style={{textShadow:'2px 2px 3px black'}}>{producto.productoDescripcion}</p>
                    </Carousel.Caption>
                </Carousel.Item>

                )
               

            )}
            
        </Carousel>
    )
}
