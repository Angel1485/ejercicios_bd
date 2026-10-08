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
    
    public static List<VideoJuego> listar() {
		
		List<VideoJuego> videojuego = new ArrayList<>(); 
		Connection con = null;
		
		try {
			
			con = Conexion.getConnection();
			
			String sql = """ 
				 	     SELECT codigo, nombre, plataforma, precio, disponible, genero
						 FROM public.videojuegos;
				         """;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); // Siempre se debe ejecutar
			
			while(rs.next()) { // Recuperamos los datos mientras existan
				
				VideoJuego vj = new VideoJuego(rs.getString("codigo"), rs.getString("nombre"), rs.getString("plataforma"), rs.getInt("precio"), rs.getBoolean("disponible"), rs.getString("genero"));
				videojuego.add(vj);
			}

		}catch(Exception e) {
			
			log.error("Error al listar" ,e.getMessage());
			
		}finally {
			try {
				con.close();
				log.info("Conexion Cerrada");
			} catch (SQLException e) {
				log.error("Error de Conexion");
			}
			
		}
		
		return videojuego;
		
	}
    
   
 

}
