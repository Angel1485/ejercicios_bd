package com.krakedev.proyectos.security;

import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

	private static final String CLAVE_SECRETA = "EstaEsUnaClaveSuperSecretaYMuyLarga1234567890!";
	private static final String EMISOR = "KrakeDevBackend";
	private static final long TIEMPO_EXPIRACION = 3600000;
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);  // Se deba añadir la dependencia y el archvio log4j2.xml

	public static String generarToken(String username, String rol) {
	    Algorithm algoritmo = Algorithm.HMAC256(CLAVE_SECRETA);  //Metodo para firmar HMAC256
	    long tiempoActual = System.currentTimeMillis();
	    Date fechaExpiracion = new Date(tiempoActual + TIEMPO_EXPIRACION);
	    
	    String tokenGenerado = JWT.create()
	            .withIssuer(EMISOR)   //Quien lo creo
	            .withSubject(username) //Dueño del token
	            .withIssuedAt(new Date(tiempoActual))
	            .withExpiresAt(fechaExpiracion)
	            .withClaim("rol", rol)
	            .sign(algoritmo);  //Firma final del token
	            
	    return tokenGenerado;
	}
	
	public static DecodedJWT validarToker(String token) {
		
		try {
			Algorithm algoritmo = Algorithm.HMAC256(CLAVE_SECRETA);
		    JWTVerifier verificador = JWT.require(algoritmo).withIssuer(EMISOR).build(); 
		    DecodedJWT tokenDecodificado = verificador.verify(token);
		    return tokenDecodificado;	
		}catch(Exception e) {
			logger.error("Error de validación del Token: " + e.getMessage());
			return null;
		}
	    
	}
}
