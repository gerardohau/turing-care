package com.example.demo.endpoint;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.data.entities.Usuario;
import com.example.demo.endpoint.message.MessageInformation;
import com.example.demo.endpoint.message.MessageLogin;
import com.example.demo.endpoint.message.MessageMedico;
import com.example.demo.endpoint.message.MessagePaciente;
import com.example.demo.endpoint.message.MessageRegistro;
import com.example.demo.endpoint.message.MessageUsuario;
import com.example.demo.service.MedicoService;
import com.example.demo.service.PacienteService;
import com.example.demo.service.RegistroService;
import com.example.demo.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {
  
  @Autowired
  private RegistroService registroService;

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private MedicoService medicoService;

  @Autowired
  private PacienteService pacienteService;

  @GetMapping("/historial_medico/{idPaciente}")
  public ResponseEntity<List<MessageRegistro> > obtenerHistorialMedico(@PathVariable Integer idPaciente) {

    return ResponseEntity.status(HttpStatus.OK).body(registroService.obtenerHistorialMedico(idPaciente) );
  }

  @GetMapping("/historial_medico/id/{idRegistro}")
  public ResponseEntity<MessageRegistro> obtenerHistorialMedicobyId(@PathVariable Integer idRegistro) {

    return ResponseEntity.status(HttpStatus.OK).body(registroService.obtenerHistorialMedicobyId(idRegistro) );
  }


  @PostMapping("/registroMedico")
  public ResponseEntity<MessageRegistro> procesoRegistro(@RequestBody MessageRegistro registroRequest){
      MessageRegistro registro = registroService.processRegister(registroRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(registro);
  }

  @DeleteMapping("/registroMedico/{idRegistro}")
  public ResponseEntity borrarRegistro(@PathVariable Integer idRegistro){
      String response = registroService.deleteRegister(idRegistro);
      return ResponseEntity
      .ok()
      .body("Respuesta: " + response);
  }

  @PostMapping("/login")
  public ResponseEntity<Usuario> login(@RequestBody MessageLogin request){
      Usuario user = usuarioService.login(request);
      return ResponseEntity.ok(user);

  }

  @GetMapping("/usuario/{idUsuario}")
  public ResponseEntity<Usuario> getUsuarioById(@PathVariable @NotBlank @NotNull Integer idUsuario){
      Usuario usuario = usuarioService.getUsuarioById(idUsuario);
      return ResponseEntity.ok().body(usuario);
  }

  @PostMapping("/registrar")
  public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody MessageUsuario request){
      Usuario usuario = usuarioService.createUsuario(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
  }

    @PutMapping("/usuario")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody MessageUsuario request){
      Usuario usuario = usuarioService.updateUsuario(request);
      return ResponseEntity.ok().body(usuario);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public ResponseEntity<Void> deleteUsuario(){
      usuarioService.deleteUsuario();
      return ResponseEntity.ok().build();
    }

  @GetMapping("/exit")
  public ResponseEntity<MessageInformation> logout(ServletRequest request){
      HttpServletRequest servRequest = (HttpServletRequest) request;
      String token = servRequest.getHeader(HttpHeaders.AUTHORIZATION);
        
      usuarioService.revokeToken(token);
      MessageInformation msg = new MessageInformation();
      msg.setContent("Cierre de sesión");
     return ResponseEntity.ok(msg);
  }

  @GetMapping("/medico/{idMedico}")
  public ResponseEntity<MessageMedico> obtenerMedico(@PathVariable Integer idMedico) {

    return ResponseEntity.status(HttpStatus.OK).body(medicoService.obtenerMedico(idMedico));
  }

  @PostMapping("/medico")
  public ResponseEntity<MessageMedico> createMedico(@Valid @RequestBody MessageMedico request){
      MessageMedico medico = medicoService.createMedico(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(medico);
  }

  @GetMapping("/pacientes/medico/{idMedico}")
  public ResponseEntity<List<MessagePaciente> > obtenerPacientes(@PathVariable Integer idMedico) {

    return ResponseEntity.status(HttpStatus.OK).body(medicoService.obtenerPacientes(idMedico));
  }


  @GetMapping("/paciente/{idPaciente}")
  public ResponseEntity<MessagePaciente> obtenerPaciente(@PathVariable Integer idPaciente) {

    return ResponseEntity.status(HttpStatus.OK).body(pacienteService.obtenerPaciente(idPaciente) );
  }

  @PostMapping("/paciente")
  public ResponseEntity<MessagePaciente> createPaciente(@Valid @RequestBody MessagePaciente request){
      MessagePaciente paciente = pacienteService.createPaciente(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
  }

}
