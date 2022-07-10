package com.main.Difficult;

import com.main.Difficult.Domain.producto.compuesto.Combo;
import com.main.Difficult.Domain.producto.simple.*;
import com.main.Difficult.Domain.usuario.Usuario;
import com.main.Difficult.Domain.producto.Lote;
import com.main.Difficult.Domain.producto.Producto;
import com.main.Difficult.Repositories.mongo.ProductoRepository;
import com.main.Difficult.Repositories.mysql.UsuarioRepository;
import com.main.Difficult.Repositories.neo4j.FacturasRepository;
import com.main.Difficult.Repositories.neo4j.UsuarioGrafosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DifficultBootstrap implements InitializingBean {
    @Autowired private UsuarioRepository repoUsuario;
    @Autowired private ProductoRepository repoProductos;
    @Autowired private UsuarioGrafosRepository repoUsuarioGrafos;
    @Autowired private FacturasRepository repoFacturas;

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Lote> lotes = new ArrayList<>();
    private Integer cantidadDeLotes = 160;
    private Logger log = LoggerFactory.getLogger(DifficultBootstrap.class);

    private void instanciarUsuarios(){
        usuarios.add(new Usuario(
                "Clemente",
                "Lopez",
                34,
                120000.00,
                "Clemente",
                "1234",
                "LziS4xI")
        );
        usuarios.add(new Usuario(
                "Bonifacio",
                "Gomez",
                46,
                1500000.00,
                "Bonifacio",
                "1234",
                "9N6dG0I")
        );
        usuarios.add(new Usuario(
                "Dalmacio",
                "Martinez",
                44,
                800000.00,
                "Dalmacio",
                "1234",
                "4CdVATz")
        );

        usuarios.add(new Usuario(
                "Emeterio",
                "Garcia",
                25,
                2500000.00,
                "Emeterio",
                "1234",
                "rUsv2PM")
        );

        usuarios.add(new Usuario(
                "Taciana",
                "Moyano",
                27,
                3500000.00,
                "Taciana",
                "1234",
                "v7W72v7")
        );

        usuarios.add(new Usuario(
                "Valentina",
                "Soto",
                23,
                1500000.00,
                "Valentina",
                "1234",
                "8XqxdXM")
        );
        usuarios.add(new Usuario(
                "Zeferina",
                "Gomez",
                34,
                120000.00,
                "Zeferina",
                "1234",
                "LziS4xI")
        );
    }
    private void instanciarProductos(){
        productos.add( new Piso("i0QUlrl",
                "Cañuelas",
                "Cerámica de interior",
                0,
                "Argentina",
                1119.00,
                50.00,
                50.00,
                Transito.NORMAL,
                Terminacion.BRILLANTE)

        );
        productos.add( new Piso("ADox8Gd",
                "Lourdes",
                "Porcelanatos",
                1,
                "Argentina",
                1679.00,
                53.00,
                53.00,
                Transito.ALTO_TRANSITO,
                Terminacion.SATINADO)

        );
        productos.add( new Piso("X078aKC",
                "Cañuelardo",
                "Cerámicas de interior",
                2,
                "Argentina",
                1279.00,
                50.00,
                50.00,
                Transito.ALTO_TRANSITO,
                Terminacion.SATINADO)

        );
        productos.add( new Piso("GBQPO2t",
                "Alberdi",
                "Cerámicas de interior",
                3,
                "Argentina",
                1389.00,
                46.0,
                46.0,
                Transito.NORMAL,
                Terminacion.SEMI_SATINADO)

        );
        productos.add( new Piso("w3hU5Rh",
                "Cortines",
                "Cerámicas de interior",
                4,
                "Argentina",
                1249.00,
                30.0,
                45.0,
                Transito.NORMAL,
                Terminacion.SEMI_SATINADO)

        );
        productos.add( new Piso("B9SKC7X",
                "Lume",
                "Revestimiento para pared",
                5,
                "Brasil",
                1249.00,
                33.0,
                60.0,
                Transito.NORMAL,
                Terminacion.SEMI_SATINADO)

        );
        productos.add( new Piso("z4L9wKa",
                "Holztek",
                "Interior",
                1,
                "China",
                3149.00,
                60.0,
                60.0,
                Transito.NORMAL,
                Terminacion.BRILLANTE)

        );
        productos.add( new Piso("Xye0Fsj",
                "Egger",
                "Colocación: Sistema Just Click. Uso recomendado: residencial",
                2,
                "China",
                2069.00,
                19.2,
                129.2,
                Transito.NORMAL,
                Terminacion.SEMI_SATINADO)

        );
        productos.add(
                new Pintura("OVDWsPw",
                        "Pro 720",
                        "Látex interior / exterior Mate",
                        3,
                        "Argentina",
                        7444.40,
                        20.0,
                        "#FFFFFF",
                        11.0)
        );
        productos.add(new Pintura("UWgeFVx",
                        "Loxon",
                        "Larga duración Látex interior Mate",
                        4,
                        "Argentina",
                        1528.36,
                        20.0,
                        "#FFFFFF",
                        13.0)
        );
        productos.add(new Pintura("InxwUZF",
                "Z10",
                "Supercubritivo Látex interior Mate",
                5,
                "Argentina",
                1528.36,
                20.0,
                "#FFFFFF",
                13.0)
        );
        productos.add(new Pintura("lDRv89C",
                "Satinol",
                "Esmalte sintético Satinado",
                1,
                "Argentina",
                4360.06,
                4.00,
                "#FFFFFF",
                13.0)
        );
        productos.add(new Pintura("zFvkLFJ",
                "Chalk Paint",
                "Pintura de tiza",
                2,
                "Argentina",
                1421.06,
                1.00,
                "#A49A9B",
                13.0)
        );
        productos.add(new Pintura("gvXh5YV",
                "Cetol Classic",
                "Satinado",
                3,
                "Argentina",
                5244.38,
                1.00,
                "#A49A9B",
                15.0)
        );

        productos.add(new Pintura("sqUUqQ7",
                "Albalatex (choreo)",
                "Látex interior Mate",
                4,
                "Argentina",
                19824.87,
                20.0,
                "#FFFFFF",
                14.0)
        );

        productos.add(new Pintura("qkslWvL",
                "Brikol",
                "Impregnante para ladrillose",
                5,
                "Argentina",
                5259.38,
                4.0,
                "#A27A65",
                13.0)
        );

    }
    private void instanciarLotes(){
        var diaCero = LocalDate.now().minusMonths(4L).minusDays(7L);
        for(int i = 0; i <cantidadDeLotes+5; i++){
            this.lotes.add(new Lote(Long.valueOf(i),diaCero, (int) (250 * Math.abs(Math.cos(i))) + 1)) ;
        }
        if(diaCero.isEqual(LocalDate.now())){
            diaCero = LocalDate.now().minusMonths(4L);
        }
        diaCero = diaCero.plusDays(1L);
    }


    private void iniciarDatos(){
        this.instanciarUsuarios();
        this.instanciarProductos();
        this.instanciarLotes();


        for (int i = 0 ; i<159; i++){
            productos.get(i % 16).agregarLote(lotes.get(i));
        }


        repoUsuario.saveAll(usuarios);
        repoUsuarioGrafos.saveAll(usuarios);
        repoProductos.saveAll(productos);
        var combo =  new Combo("B7yWM0f",
                "combo",
                "pisos y pinturas",
                1,
                "Argentina",
                0.0);

        for(int i = 0 ; i < 3; i++){
            combo.agregarProductoCombo((ProductoSimple) productos.get(i), productos.get(i).getLotes().stream().findFirst().get(),i+1);
            combo.agregarLote(lotes.get(160+i));
        }
        productos.add(combo);
        repoProductos.save(combo);
    }
    @Override
    public void afterPropertiesSet() throws Exception{
        log.info("**********************************************************************************************");
        log.info("Running initialization");
        log.info("**********************************************************************************************");
        this.iniciarDatos();
        log.info("**********************************************************************************************");
        log.info("Finish initialization");
        log.info("**********************************************************************************************");
    }
}
