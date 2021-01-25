package com.example.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.example.demo.data.dao.MedicoRepository;
import com.example.demo.data.dao.PacienteRepository;
import com.example.demo.data.dao.RegistroRepository;
import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Paciente;
import com.example.demo.data.entities.Registro;
import com.example.demo.endpoint.message.MessageMedico;
import com.example.demo.endpoint.message.MessagePaciente;
import com.example.demo.endpoint.message.MessageRegistro;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Paciente;
import com.example.demo.data.entities.Registro;
import com.example.demo.endpoint.message.RegistroRequest;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
  
  public Registro obtenerRegistro(Integer idRegistro) {
    
    Optional<Registro> optional;

    try { 
      optional = registroRepository.findById(idRegistro);
      return optional.get();

    } catch(NullPointerException | NoSuchElementException e) {
      throw new ResourceNotFoundException("El registro no se ha encontrado");
    }
  }
  
  public MessageRegistro processRegister(MessageRegistro registroRequest){
   
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
    
    MessageRegistro message = new MessageRegistro();
    
    MessageMedico mMedico = new MessageMedico();
    mMedico.setMedicoId( medico.getMedicoId()   );
    mMedico.setNombre( medico.getNombre() );
    mMedico.setEmail( medico.getEmail() );

    MessagePaciente mPaciente = new MessagePaciente();
    mPaciente.setPacienteId( paciente.getPacienteId()  );
    mPaciente.setNombre( paciente.getNombre()  );
    mPaciente.setEmail( paciente.getEmail()  );

    message = this.convertRequistroToRegistroMessage(message, registro);
    message.setIdRegistro( registro.getRegistroId() );
    message.setMedico(mMedico);
    message.setPaciente(mPaciente);

    registroRequest = message;

    return registroRequest;
  }

  public List<MessageRegistro> obtenerHistorialMedico(Integer pacienteId) {
    Paciente paciente = pacienteRepository.findById(pacienteId).get();
    //paciente.getRegistros().size();
    List<Registro> registros = paciente.getRegistros();
    List<MessageRegistro> messageRegistros = new LinkedList<>();

    for( Registro registro : registros) {
      
      Medico medico = new Medico();
      medico = registro.getMedico();
      
      MessageRegistro message = new MessageRegistro();
    
      MessageMedico mMedico = new MessageMedico();
      mMedico.setMedicoId( medico.getMedicoId()   );
      mMedico.setNombre( medico.getNombre() );
      mMedico.setEmail( medico.getEmail() );

      MessagePaciente mPaciente = new MessagePaciente();
      mPaciente.setPacienteId( paciente.getPacienteId()  );
      mPaciente.setNombre( paciente.getNombre()  );
      mPaciente.setEmail( paciente.getEmail()  );

      message = this.convertRequistroToRegistroMessage(message, registro);
      message.setIdRegistro( registro.getRegistroId() );
      message.setMedico(mMedico);
      message.setPaciente(mPaciente);

      messageRegistros.add(message);

    }

    return messageRegistros;
  }

  public String deleteRegister(Integer idRegistro) {
    try {
      registroRepository.deleteById(idRegistro);

      return "Borrado Exitosamente";
    } catch (EmptyResultDataAccessException e) {

      return "Registro inexistente";
    }
  }

  public Registro convertRegistroRequestToRegistro(MessageRegistro registroRequest, Registro registro, boolean isNew ){
    Registro nuevoRegistro = registro;
    Date date = new Date();
    
    nuevoRegistro.setAsunto( isNew 
      ? registroRequest.getAsunto()
      : registroRequest.getAsunto() != null
        ? registroRequest.getAsunto()
        : nuevoRegistro.getAsunto()
    );
    
    nuevoRegistro.setDescripcion( isNew 
      ? registroRequest.getDescripcion()
      : registroRequest.getDescripcion() != null 
        ? registroRequest.getDescripcion()
        : nuevoRegistro.getDescripcion()
    );

    nuevoRegistro.setMedicamentoRecetado( isNew 
    ? registroRequest.getMedicamentoRecetado()
    : registroRequest.getMedicamentoRecetado() != null 
      ? registroRequest.getMedicamentoRecetado()
      : nuevoRegistro.getMedicamentoRecetado()
    );

    nuevoRegistro.setObservaciones( isNew 
    ? registroRequest.getObservaciones()
    : registroRequest.getObservaciones() != null 
      ? registroRequest.getObservaciones()
      : nuevoRegistro.getObservaciones()
    );

    nuevoRegistro.setSintomas( isNew 
    ? registroRequest.getSintomas()
    : registroRequest.getSintomas() != null 
      ? registroRequest.getSintomas()
      : nuevoRegistro.getSintomas()
    );

    nuevoRegistro.setSeguimientoTratamiento( isNew 
    ? registroRequest.getSeguimientoTratamiento()
    : registroRequest.getSeguimientoTratamiento() != null 
      ? registroRequest.getSeguimientoTratamiento()
      : nuevoRegistro.getSeguimientoTratamiento()
    );

    nuevoRegistro.setTipoTratamiento( isNew 
    ? registroRequest.getTipoTratamiento()
    : registroRequest.getTipoTratamiento() != null 
      ? registroRequest.getTipoTratamiento()
      : nuevoRegistro.getTipoTratamiento()
    );
    
    nuevoRegistro.setFechaActualizacion(date);

    nuevoRegistro.setFechaRegistro( isNew ? date : nuevoRegistro.getFechaRegistro() );

    nuevoRegistro.setFechaCita(isNew 
    ? utilService.convertStringToDate(registroRequest.getFechaCita())
    : registroRequest.getFechaCita() != null 
      ? utilService.convertStringToDate(registroRequest.getFechaCita())
      : nuevoRegistro.getFechaCita()
    );

    return nuevoRegistro;
  }


  public MessageRegistro convertRequistroToRegistroMessage(MessageRegistro mensajeRegistro, Registro registro){
    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");  

    mensajeRegistro.setAsunto( registro.getAsunto()  );
    mensajeRegistro.setDescripcion( registro.getDescripcion()  );
    mensajeRegistro.setObservaciones( registro.getObservaciones()  );
    mensajeRegistro.setMedicamentoRecetado( registro.getMedicamentoRecetado()  );
    mensajeRegistro.setSeguimientoTratamiento( registro.getSeguimientoTratamiento()  );
    mensajeRegistro.setTipoTratamiento( registro.getTipoTratamiento()  );
    mensajeRegistro.setSintomas( registro.getSintomas()  );
    mensajeRegistro.setFechaCita( dateFormat.format( registro.getFechaCita()  )  );
    mensajeRegistro.setFechaCreado( dateFormat.format( registro.getFechaRegistro()  )  );
    mensajeRegistro.setFechaActualizado( dateFormat.format( registro.getFechaActualizacion()  )  );
        
    return mensajeRegistro;

  }
}
