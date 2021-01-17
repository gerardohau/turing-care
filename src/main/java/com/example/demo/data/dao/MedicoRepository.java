package com.example.demo.data.dao;

import java.util.List;

import com.example.demo.data.entities.Medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
  
}
