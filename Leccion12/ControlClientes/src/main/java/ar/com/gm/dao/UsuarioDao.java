package ar.com.gm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.gm.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
