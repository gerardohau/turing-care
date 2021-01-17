package com.example.demo.service;

import java.util.Date;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


import com.example.demo.data.dao.RegistroRepository;
import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Paciente;
import com.example.demo.data.entities.Registro;
import com.example.demo.endpoint.message.RegistroRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Medico;
import com.example.demo.model.Paciente;
import com.example.demo.model.Registro;
import com.example.demo.repository.RegistroRepository;
import com.example.demo.repository.PacienteRepository;
import com.example.demo.request.RegistroRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {
  
  @Autowired
  private utilsService utilService;

  @Autowired
  private RegistroRepository registroRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  
  public Registro processRegister(RegistroRequest registroRequest){
   
    boolean isUpdate = registroRequest.getIdRegistro() != null ? true : false;
    
    Registro registro = new Registro();
    Medico medico = new Medico();
    Paciente paciente = new Paciente();
    
    if( isUpdate ){
      Optional<Registro> registroOpt = registroRepository.findById( registroRequest.getIdRegistro() );
      
      if( registroOpt.isPresent() ){
        registro = registroOpt.get();
      }else{
        throw new ResourceNotFoundException("El registro no existe con id: "+registroRequest.getIdRegistro());
      }
    }

    //validar si existe el paciente - medico
    registro = this.convertRegistroRequestToRegistro(registroRequest, registro, isUpdate);
    
    registro.setMedico(medico);
    registro.setPaciente(paciente);
    
    //registro = registroRepository.save(registro);
    
    return registro;
  }

  public List<Registro> obtenerHistorialMedico(Integer pacienteId) {
    Paciente paciente = pacienteRepository.findById(pacienteId).get();
    paciente.getRegistros().size();
    System.out.println("el id del paciente");
    System.out.println(paciente.getPacienteId());
    return paciente.getRegistros();
  }

  public Registro convertRegistroRequestToRegistro(RegistroRequest registroRequest, Registro registro, boolean isUpdate ){
    Registro nuevoRegistro = registro;
    Date date = new Date();
    
    nuevoRegistro.setAsunto( isUpdate 
      ? registroRequest.getAsunto()
      : registroRequest.getAsunto() != null && registroRequest.getAsunto().isEmpty()
        ? registroRequest.getAsunto()
        : nuevoRegistro.getAsunto()
    );
    
    nuevoRegistro.setDescripcion( isUpdate 
      ? registroRequest.getDescripcion()
      : registroRequest.getDescripcion() != null && registroRequest.getDescripcion().isEmpty()
        ? registroRequest.getDescripcion()
        : nuevoRegistro.getDescripcion()
    );

    nuevoRegistro.setFechaActualizacion(date);

    nuevoRegistro.setFechaRegistro( isUpdate ? nuevoRegistro.getFechaRegistro() : date );

    nuevoRegistro.setFechaCita(isUpdate 
    ? utilService.convertStringToDate(registroRequest.getFechaCita())
    : registroRequest.getFechaCita() != null && registroRequest.getFechaCita().isEmpty()
      ? utilService.convertStringToDate(registroRequest.getFechaCita())
      : nuevoRegistro.getFechaCita()
    );

    return nuevoRegistro;
  }


}
