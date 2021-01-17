package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import com.example.demo.data.dao.MedicoRepository;
import com.example.demo.data.dao.PacienteRepository;
import com.example.demo.data.dao.RegistroRepository;
import com.example.demo.data.entities.Clinica;
import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Paciente;
import com.example.demo.data.entities.Registro;
import com.example.demo.endpoint.message.MessageRegistro;
import com.example.demo.exception.ResourceNotFoundException;

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

  @Autowired
  private MedicoRepository medicoRepository;
  
  public Registro processRegister(MessageRegistro registroRequest){
   
    boolean isNew = registroRequest.getIdRegistro() == null;
    
    Registro registro = new Registro();
    Medico medico = new Medico();
    Paciente paciente = new Paciente();
    
    if( !isNew ){
      Optional<Registro> registroOpt = registroRepository.findById( registroRequest.getIdRegistro() );
      
      if( registroOpt.isPresent() ){
        registro = registroOpt.get();
      }else{
        throw new ResourceNotFoundException("El registro no existe con id: "+registroRequest.getIdRegistro());
      }
    }

    //validar si existe el paciente - medico
    Optional<Medico> medicoOpt = medicoRepository.findById( registroRequest.getIdMedico() ); 
    Optional<Paciente> pacienteOpt = pacienteRepository.findById( registroRequest.getIdPaciente() ); 
    
    if( pacienteOpt.isEmpty() || medicoOpt.isEmpty()  ){
      throw new ResourceNotFoundException("El medico o el paciente no existe");
    }
    registro = this.convertRegistroRequestToRegistro(registroRequest, registro, isNew);
    
    medico = medicoOpt.get();
    paciente = pacienteOpt.get();

    registro.setMedico(medico);
    registro.setPaciente(paciente);
    
    registro = registroRepository.save(registro);
     //evitar ciclado
    medico.setPacientes(null);
    medico.setRegistros(null);
    medico.setUsuario(null);
    medico.setClinica(null);
    paciente.setRegistros(null);
    paciente.setMedico(null);
    ///
    return registro;
  }


  public Registro convertRegistroRequestToRegistro(MessageRegistro registroRequest, Registro registro, boolean isNew ){
    Registro nuevoRegistro = registro;
    Date date = new Date();
    
    nuevoRegistro.setAsunto( isNew 
      ? registroRequest.getAsunto()
      : registroRequest.getAsunto() != null && registroRequest.getAsunto().isEmpty()
        ? registroRequest.getAsunto()
        : nuevoRegistro.getAsunto()
    );
    
    nuevoRegistro.setDescripcion( isNew 
      ? registroRequest.getDescripcion()
      : registroRequest.getDescripcion() != null && registroRequest.getDescripcion().isEmpty()
        ? registroRequest.getDescripcion()
        : nuevoRegistro.getDescripcion()
    );

    nuevoRegistro.setFechaActualizacion(date);

    nuevoRegistro.setFechaRegistro( isNew ? date : nuevoRegistro.getFechaRegistro() );

    nuevoRegistro.setFechaCita(isNew 
    ? utilService.convertStringToDate(registroRequest.getFechaCita())
    : registroRequest.getFechaCita() != null && registroRequest.getFechaCita().isEmpty()
      ? utilService.convertStringToDate(registroRequest.getFechaCita())
      : nuevoRegistro.getFechaCita()
    );

    return nuevoRegistro;
  }


}
