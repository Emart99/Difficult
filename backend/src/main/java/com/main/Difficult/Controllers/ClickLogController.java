package com.main.Difficult.Controllers;

import com.main.Difficult.Deserializers.LoggerDTO;
import com.main.Difficult.Deserializers.ProductoDTO;
import com.main.Difficult.Domain.ClickLog;
import com.main.Difficult.Services.ClickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
public class ClickLogController {
    @Autowired private ClickLogService clickLogService;

    @GetMapping("/logs/{id}")
    public ClickLog logsUsuario(@PathVariable Long id){
        return clickLogService.traerByIdUsuario(id);
    }

    @PostMapping("/logs")
    public void crearLog(@RequestBody LoggerDTO clickLog){
        clickLogService.crearLog(clickLog);
    }

    @GetMapping("/logs/productosMasClickeados")
    public List<ProductoDTO> productosMasClickeados(){
        return clickLogService.recomendacionesGeneral();
    }


}
