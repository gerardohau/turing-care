package com.example.demo.rest;

import com.example.demo.model.Registro;
import com.example.demo.request.RegistroRequest;
import com.example.demo.service.RegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistroRest {
  
  @Autowired
  private RegistroService registroService;

  @PostMapping("/registro/")
  public ResponseEntity<Registro> procesoRegistro(@RequestBody RegistroRequest registroRequest){
    Registro registro = registroService.processRegister(registroRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(registro);
  }
}
