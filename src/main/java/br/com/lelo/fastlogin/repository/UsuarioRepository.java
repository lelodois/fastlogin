package br.com.lelo.fastlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lelo.fastlogin.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
