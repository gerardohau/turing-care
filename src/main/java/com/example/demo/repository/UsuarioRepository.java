package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Medico;
import com.example.demo.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  
  Usuario findByMedico(Medico medico);
  Usuario findByToken(String token); 
}
