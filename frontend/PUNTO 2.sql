use difficultapp;
-- PUNTO 2 INICIO **
CREATE TABLE IF NOT EXISTS name_history
	(id_history BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_cambio DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario BIGINT,
    nombreNuevo VARCHAR(255),
    foreign key (id_usuario) references usuario(id)
);
    
 
DROP TRIGGER IF EXISTS name_changed_count;
    
delimiter // 
CREATE TRIGGER name_changed_count
	AFTER UPDATE ON usuario 
		FOR EACH ROW
			BEGIN 
				IF NEW.nombre != OLD.nombre
					THEN INSERT INTO name_history (id_usuario, nombreNuevo) VALUES (NEW.id, NEW.nombre); 
				END IF;
			END;// 
-- PUNTO 2 FIN **

-- PUNTO 4 INICIO **
ALTER TABLE usuario MODIFY saldo double not null;
-- PUNTO 4 FIN **

-- PUNTO 3 INICIO **
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `usuarios_con_mas_de_3_compras` AS
    SELECT 
        `usuario`.`id` AS `id`,
        `usuario`.`apellido` AS `apellido`,
        `usuario`.`edad` AS `edad`,
        `usuario`.`nombre` AS `nombre`,
        `usuario`.`saldo` AS `saldo`,
        `usuario`.`usuario_nombre` AS `usuario_nombre`,
        COUNT(`usuario`.`id`) AS `cantidad_compras`
    FROM
        (`usuario`
        JOIN `factura` ON ((`factura`.`usuario_id` = `usuario`.`id`)))
    GROUP BY `usuario`.`id`
    HAVING (`cantidad_compras` > 3);
-- PUNTO 3 FIN **

-- PUNTO 1 INICIO **
CREATE DEFINER=`root`@`localhost` PROCEDURE `productos_del_usuario`(IN name VARCHAR(20))
BEGIN
	SELECT * FROM usuario
    
    INNER JOIN factura on factura.usuario_id = usuario.id
   
    INNER JOIN factura_producto_lote on 
          factura_producto_lote.factura_id = factura.orden_de_compra

	INNER JOIN producto on factura_producto_lote.producto_id = producto.id

    WHERE usuario.nombre = name;
END
-- PUNTO 1 FIN **