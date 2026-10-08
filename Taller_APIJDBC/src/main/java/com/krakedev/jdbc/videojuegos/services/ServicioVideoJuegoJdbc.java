package com.krakedev.jdbc.videojuegos.services;
import java.util.List;
import org.springframework.stereotype.Service;
import com.krakedev.jdbc.videojuegos.VideoJuegoJdbc;
import com.krakedev.videojuegos.entidades.VideoJuego;

@Service
public class ServicioVideoJuegoJdbc {
	
	 //Crear 
    public VideoJuego ingresarCliente(VideoJuego videojuego) {
       
    	VideoJuego videoRecuperado = VideoJuegoJdbc.insertar(videojuego.getCodigo(), videojuego.getNombre(), videojuego.getPlataforma(), videojuego.getPrecio(), videojuego.isDisponible(), videojuego.getGenero());
        return videoRecuperado;
    }
    
    // Lista todos los video juegos
    public List<VideoJuego> listarVideoJuego() {
    	
    	return  VideoJuegoJdbc.listar();
    }
    
    //Buscar videojuego codigo
    public VideoJuego buscarCodigo(String codigo) {
        
    	return VideoJuegoJdbc.buscar(codigo);
        
    }

    //Actualizar VideoJuego
    public VideoJuego actualizar(String codigo , VideoJuego videoActualizado) {
        
    	return VideoJuegoJdbc.actualizar(codigo, videoActualizado.getNombre(), videoActualizado.getPlataforma(), videoActualizado.getPrecio(), videoActualizado.isDisponible(), videoActualizado.getGenero());
        
    }

    //Eliminar VideoJuego
    public boolean eliminarVideoJuego(String codigo) {
    	return  VideoJuegoJdbc.eliminar(codigo);
         
    }

}
