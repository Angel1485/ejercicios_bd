package com.krakedev.jdbc.videojuegos;
import java.sql.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.VideoJuego;

public class VideoJuegoJdbc {
	
    private static final Logger log = LoggerFactory.getLogger(VideoJuegoJdbc.class);

    //Insertar
    public static VideoJuego insertar(VideoJuego videojuego) {
    	
    	Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.getConnection();
            
            String sql = """
	        		INSERT INTO public.videojuegos
					(codigo, nombre, plataforma, precio, disponible, genero)
					VALUES(?, ?, ?, ?, ?, ?);
        		    """;
            
            ps = con.prepareStatement(sql);
            ps.setString(1, videojuego.getCodigo());
            ps.setString(2, videojuego.getNombre());
            ps.setString(3, videojuego.getPlataforma());
            ps.setDouble(4, videojuego.getPrecio());
            ps.setBoolean(5, videojuego.isDisponible());
            ps.setString(6, videojuego.getGenero());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                log.info("Videojuego insertado: {}", videojuego.getCodigo());
                return videojuego;
            }
        } catch (Exception e) {
            log.error("Error al insertar el videojuego", e);
        } finally {
            try {
                if (ps != null) ps.close();
                con.close();
            } catch (SQLException e) {
            	log.error("Error de Conexion");
            }
        }
        return null;
    }
    
   
 

}
