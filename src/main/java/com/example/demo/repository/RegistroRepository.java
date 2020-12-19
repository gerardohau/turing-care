package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Medico;
import com.example.demo.model.Registro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
  
  List<Registro> findByMedico(Medico medico);
}
