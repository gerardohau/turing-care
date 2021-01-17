package com.example.demo.rest;

import java.util.List;

import com.example.demo.data.entities.Registro;
import com.example.demo.service.RegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistorialMedicoRest {
  
    @Autowired
    private RegistroService registroService;

  @GetMapping("/historial_medico/{idPaciente}")
  public ResponseEntity<List<Registro>> obtenerHistorialMedico(@PathVariable Integer idPaciente) {
    System.out.println("Lo que recive");
    System.out.println(idPaciente);

    return ResponseEntity.status(HttpStatus.CREATED).body(registroService.obtenerHistorialMedico(idPaciente));
  }
}