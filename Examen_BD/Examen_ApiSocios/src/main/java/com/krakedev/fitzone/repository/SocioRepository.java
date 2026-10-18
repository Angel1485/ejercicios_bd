package com.krakedev.fitzone.repository;

import com.krakedev.fitzone.entidades.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long>{

	List<Socio> findByActivo(boolean activo);
}
