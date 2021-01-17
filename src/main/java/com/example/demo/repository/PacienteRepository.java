package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Medico;
import com.example.demo.model.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
  
}
