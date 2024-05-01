package ar.com.gm.dao;

import org.springframework.data.repository.CrudRepository;
import ar.com.gm.domain.Persona;

public interface PersonaDao extends CrudRepository<Persona, Long>{
    
}
