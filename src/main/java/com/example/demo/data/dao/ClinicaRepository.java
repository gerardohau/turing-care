package com.example.demo.data.dao;

import java.util.List;

import com.example.demo.data.entities.Clinica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {
  
}