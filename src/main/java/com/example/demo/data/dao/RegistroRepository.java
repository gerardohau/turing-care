package com.example.demo.data.dao;

import java.util.Optional;
import java.util.List;

import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Registro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
  
  List<Registro> findByMedico(Medico medico);
  Optional<Registro> findById(Integer idRegistro);
}
