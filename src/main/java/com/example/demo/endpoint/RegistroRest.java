package com.example.demo.endpoint;

import com.example.demo.data.entities.Registro;
import com.example.demo.endpoint.message.RegistroRequest;
import com.example.demo.service.RegistroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class RegistroRest {
  
  @Autowired
  private RegistroService registroService;

  @PostMapping("/registro/")
  public ResponseEntity<Registro> procesoRegistro(@RequestBody RegistroRequest registroRequest){
    Registro registro = registroService.processRegister(registroRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(registro);
  }

  @GetMapping("/registro/{idRegistro}")
  public ResponseEntity<Registro> obtenerRegistro(@PathVariable Integer idRegistro) {

    return ResponseEntity.status(HttpStatus.OK).body(registroService.obtenerRegistro(idRegistro));
  }

  @DeleteMapping("/registro/{idRegistro}")
  public ResponseEntity borrarRegistro(@PathVariable Integer idRegistro){
      String response = registroService.deleteRegister(idRegistro);
      return ResponseEntity
      .ok()
      .body("Respuesta: " + response);
  }
}
