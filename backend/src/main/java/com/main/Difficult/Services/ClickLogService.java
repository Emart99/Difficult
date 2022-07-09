package com.main.Difficult.Services;

import com.main.Difficult.Deserializers.LoggerDTO;
import com.main.Difficult.Deserializers.ProductoDTO;
import com.main.Difficult.Domain.ClickLog;
import com.main.Difficult.Exceptions.BadRequestException;
import com.main.Difficult.Repositories.mongo.ClickLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClickLogService {
    @Autowired private ClickLogRepository repoClickLog;

    @Transactional
    public ClickLog traerByIdUsuario(Long id){
        return repoClickLog.nombre(id).orElseThrow(()-> new BadRequestException("El usuario es re gil y no vio ningún producto"));
    }
    @Transactional
    public void crearLog(LoggerDTO clickLog){
        repoClickLog.save(new ClickLog(
                clickLog.idProducto,
                clickLog.idUsuario,
                clickLog.productoImagen,
                clickLog.productoNombre,
                clickLog.productoDescripcion,
                clickLog.productoValoracion));
    }
    @Transactional
    public List<ProductoDTO> recomendacionesGeneral(){
        return repoClickLog.productosMasVistos();
    }

}
