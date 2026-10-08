package com.krakedev.jdbc.videojuegos.controller;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.krakedev.jdbc.videojuegos.services.ServicioVideoJuegoJdbc;
import com.krakedev.videojuegos.entidades.VideoJuego;

@RestController
@RequestMapping ("/jdbc/videojuegos")
public class VideojuegoJdbcController {
	
	private final ServicioVideoJuegoJdbc servicioVideoJuegoJdbc;

    // Inyeccion por constructor
    public VideojuegoJdbcController(ServicioVideoJuegoJdbc servicioVideoJuegoJdbc) {
        this.servicioVideoJuegoJdbc = servicioVideoJuegoJdbc;
    }
 
    @PostMapping("/insertVideoJuego")
    public VideoJuego crearVideoJuego(@RequestBody VideoJuego videojuego) {
    	return servicioVideoJuegoJdbc.ingresarCliente(videojuego);
    }

    @GetMapping("/listarVideoJuego")
    public List<VideoJuego> listar() {
        return servicioVideoJuegoJdbc.listarVideoJuego();
    }

    @GetMapping("/{codigo}")
    public VideoJuego buscarCodigo(@PathVariable String codigo) {
        return servicioVideoJuegoJdbc.buscarCodigo(codigo);
    }
    
    @PutMapping("/{codigo}")
    public VideoJuego actualizar(@PathVariable String codigo, @RequestBody VideoJuego videojuego) {
        return servicioVideoJuegoJdbc.actualizar(codigo, videojuego);
    }
    
    @DeleteMapping("/{codigo}")
    public boolean eliminar(@PathVariable String codigo) {
    	
    	VideoJuego vj = servicioVideoJuegoJdbc.buscarCodigo(codigo);  //Primero envio a buscar el codigo 
    	if(vj != null) {
    		return servicioVideoJuegoJdbc.eliminarVideoJuego(codigo);
    	}
     return false;   
    }


}
