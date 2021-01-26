package com.example.demo.service;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.data.dao.ClinicaRepository;
import com.example.demo.data.dao.MedicoRepository;
import com.example.demo.data.dao.UsuarioRepository;
import com.example.demo.data.entities.Clinica;
import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Paciente;
import com.example.demo.data.entities.Usuario;
import com.example.demo.endpoint.message.MessageMedico;
import com.example.demo.endpoint.message.MessagePaciente;
import com.example.demo.exception.ResourceNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService{

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ClinicaRepository clinicaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public MessageMedico createMedico(MessageMedico medico) {
        Medico medico_nuevo = new Medico();
        
        Optional<Clinica> clinicaOpt = clinicaRepository.findById(medico.getClinicaId()); 
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(medico.getUsuarioId() );
        medico_nuevo.setNombre(medico.getNombre());
        medico_nuevo.setClinica(clinicaOpt.get());
        medico_nuevo.setUsuario( usuarioOpt.get() );
        medico_nuevo.setApellidoPaterno(medico.getApellidoPaterno());
        medico_nuevo.setApellidoMaterno(medico.getApellidoMaterno());
        medico_nuevo.setCurp(medico.getCurp());
        medico_nuevo.setUrlCedulta(medico.getUrlCedulta());
        medico_nuevo.setUrlFoto(medico.getUrlFoto());
        medico_nuevo.setEspecialidad(medico.getEspecialidad());
        medico_nuevo.setTelefono(medico.getTelefono());
        medico_nuevo.setStatus(medico.getStatus());
        medico_nuevo.setEmail(medico.getEmail());


        
        

        Medico medico_creado = medicoRepository.save(medico_nuevo);

        MessageMedico nuevo_medico = new MessageMedico();
        nuevo_medico.setMedicoId(medico_creado.getMedicoId());
        nuevo_medico.setNombre(medico_creado.getNombre());
        nuevo_medico.setClinicaId(medico_creado.getClinica().getClinicaId());
        nuevo_medico.setUsuarioId( medico_creado.getUsuario().getUsuarioId());
        nuevo_medico.setApellidoPaterno(medico_creado.getApellidoPaterno());
        nuevo_medico.setApellidoMaterno(medico_creado.getApellidoMaterno());
        nuevo_medico.setCurp(medico_creado.getCurp());
        nuevo_medico.setUrlCedulta(medico_creado.getUrlCedulta());
        nuevo_medico.setUrlFoto(medico_creado.getUrlFoto());
        nuevo_medico.setEspecialidad(medico_creado.getEspecialidad());
        nuevo_medico.setTelefono(medico_creado.getTelefono());
        nuevo_medico.setStatus(medico_creado.getStatus());
        nuevo_medico.setEmail(medico_creado.getEmail());
        
        return nuevo_medico;
    }

    public MessageMedico obtenerMedico(Integer idMedico){
        Optional<Medico> medicoOpt = medicoRepository.findById(idMedico); 
        
        if(medicoOpt.isEmpty() ){
           throw new ResourceNotFoundException("El medico o el paciente no existe");
        }

        MessageMedico medico_obtenido = new MessageMedico();

        medico_obtenido.setMedicoId(medicoOpt.get().getMedicoId());
        medico_obtenido.setNombre(medicoOpt.get().getNombre());
        medico_obtenido.setClinicaId(medicoOpt.get().getClinica().getClinicaId());
        medico_obtenido.setUsuarioId( medicoOpt.get().getUsuario().getUsuarioId());
        medico_obtenido.setApellidoPaterno(medicoOpt.get().getApellidoPaterno());
        medico_obtenido.setApellidoMaterno(medicoOpt.get().getApellidoMaterno());
        medico_obtenido.setCurp(medicoOpt.get().getCurp());
        medico_obtenido.setUrlCedulta(medicoOpt.get().getUrlCedulta());
        medico_obtenido.setUrlFoto(medicoOpt.get().getUrlFoto());
        medico_obtenido.setEspecialidad(medicoOpt.get().getEspecialidad());
        medico_obtenido.setTelefono(medicoOpt.get().getTelefono());
        medico_obtenido.setStatus(medicoOpt.get().getStatus());
        medico_obtenido.setEmail(medicoOpt.get().getEmail());

        return medico_obtenido;

    }


    public List<MessagePaciente> obtenerPacientes(Integer idMedico) {
        Optional<Medico> medicoOpt = medicoRepository.findById(idMedico); 
        List<Paciente> pacientes = medicoOpt.get().getPacientes();
        List<MessagePaciente> messagePacientes = new LinkedList<>();
    
        for( Paciente paciente : pacientes) {
          
          MessagePaciente message = new MessagePaciente();
        
          message.setPacienteId(paciente.getPacienteId());
          message.setNombre(paciente.getNombre());
          message.setClinicaId(paciente.getClinica().getClinicaId());
          message.setMedicoId(paciente.getMedico().getMedicoId());
          message.setApellidoPaterno(paciente.getApellidoPaterno());
          message.setApellidoMaterno(paciente.getApellidoMaterno());
          message.setEmail(paciente.getEmail());
          message.setTelefono(paciente.getTelefono());
          message.setAlergias(paciente.getAlergias());
          message.setSexo(paciente.getSexo());
          message.setOperacionesPrevias(paciente.getOperacionesPrevias());
          message.setEnfermedadesCronicas(paciente.getEnfermedadesCronicas());
          message.setTratamientosVigentes(paciente.getTratamientosVigentes());
          message.setFechaNacimiento(paciente.getFechaNacimiento().toString());
    
          messagePacientes.add(message);
    
        }
    
        return messagePacientes;
      }
	

}