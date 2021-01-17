package com.example.demo.rest;

import java.util.List;
import java.util.ArrayList;
import com.example.demo.data.entities.Registro;
import com.example.demo.service.RegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HistorialMedicoRest {
  
    @Autowired
    private RegistroService registroService;

  @GetMapping("/historial_medico/{idPaciente}")
  public ResponseEntity<List<Registro>> obtenerHistorialMedico(@PathVariable Integer idPaciente) {

    return ResponseEntity.status(HttpStatus.OK).body(registroService.obtenerHistorialMedico(idPaciente));
  }
}