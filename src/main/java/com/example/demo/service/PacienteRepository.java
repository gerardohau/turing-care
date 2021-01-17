package com.example.demo.data.dao;

import java.util.List;


import com.example.demo.data.entities.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
  
}
