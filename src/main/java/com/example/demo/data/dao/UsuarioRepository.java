package com.example.demo.data.dao;

import java.util.Optional;

import com.example.demo.data.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Usuario findByToken(String token); 
  Optional<Usuario> findByUsuario(String usuario);
}
