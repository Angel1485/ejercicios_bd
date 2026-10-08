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
    public static VideoJuego insertar(String codigo, String nombre, String plataforma ,double precio, boolean disponible, String genero) {
    	
    	Connection con = null;
        PreparedStatement ps = null;
        VideoJuego videojuego = null;

        try {
            con = Conexion.getConnection();
            
            String sql = """
	        		INSERT INTO public.videojuegos
					(codigo, nombre, plataforma, precio, disponible, genero)
					VALUES(?, ?, ?, ?, ?, ?);
        		    """;
            
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, plataforma);
            ps.setDouble(4, precio);
            ps.setBoolean(5, disponible);
            ps.setString(6, genero);
            
            videojuego =  new VideoJuego(codigo, nombre, plataforma, precio, disponible, genero);

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
    
public static VideoJuego buscar(String cedula) {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		ResultSet rs = null;
		VideoJuego videojuego =  null;
		
		try {
			
			con = Conexion.getConnection();
			
			sql = """ 
		 	      SELECT * FROM public.videojuegos
		 	      WHERE codigo = ?;
		          """;
			
			ps = con.prepareStatement(sql);	
			ps.setString(1, cedula);
			rs = ps.executeQuery(); // Siempre se debe ejecutar
			
			if (rs.next()) {  //Devuelve verdadero o falso
				
				videojuego = new VideoJuego(rs.getString("codigo"), rs.getString("nombre"), rs.getString("plataforma"), rs.getInt("precio"), rs.getBoolean("disponible"), rs.getString("genero"));
				
			}

		}catch(Exception e) {
			
			log.error("Error al buscar el codigo" ,e.getMessage());
			
		}finally {
			try {
				con.close();
				rs.close();
				ps.close();
				log.info("Conexion Cerrada");
			} catch (SQLException e) {
				log.error("Error de Conexion");
			}
			
		}
		
		return videojuego;
		
	}

	public static VideoJuego actualizar(String codigo, String nuevoNombre,String nuevaPlataforma, double nuevoPrecio, boolean nuevoDisponible, String nuevoGenero) {
	
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		VideoJuego videojuego =  null;
		
		try {
			
			con = Conexion.getConnection();
			
			sql = """ 
				  UPDATE public.videojuegos
				  SET nombre=?, plataforma=?, precio=?, disponible=?, genero=?
				  WHERE codigo=?;
			      """;
			
			ps = con.prepareStatement(sql);	
			ps.setString(1, nuevoNombre);
			ps.setString(2, nuevaPlataforma);
			ps.setDouble(3, nuevoPrecio);
			ps.setBoolean(4, nuevoDisponible);
			ps.setString(5, nuevoGenero);
			ps.setString(6, codigo);
			
			int filas = ps.executeUpdate();
			videojuego = new VideoJuego(codigo, nuevoNombre, nuevaPlataforma, nuevoPrecio, nuevoDisponible, nuevoGenero); //Cargo el cliente con los nuevos datos
			
			log.info("Filas actualziadas: " + filas);
			
		}catch(Exception e) {
			
			log.error("Error al buscar la cedula" ,e.getMessage());
			
		}finally {
			try {
				con.close();
				ps.close();
				log.info("Conexion Cerrada");
			} catch (SQLException e) {
				log.error("Error de Conexion");
			}
			
		}
		
		return videojuego;
	
	}
    
	public static boolean eliminar(String codigo) {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";
		VideoJuego videoJuego =  null;
		
		try {
			
			con = Conexion.getConnection();
			
			sql = """ 
			      DELETE FROM public.videojuegos
				  WHERE codigo=?;
			      """;
			
			ps = con.prepareStatement(sql);	
			ps.setString(1, codigo);
			
			int filas = ps.executeUpdate();
			log.info("Filas eliminadas: " + filas);
			return true;
		}catch(Exception e) {
			
			log.error("Error al eliminar" ,e.getMessage());
			return false;
			
		}finally {
			try {
				con.close();
				ps.close();
				log.info("Conexion Cerrada");
			} catch (SQLException e) {
				log.error("Error de Conexion");
			}
			
		}
				
	}
 

}
