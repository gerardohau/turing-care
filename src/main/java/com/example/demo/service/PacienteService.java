package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import com.example.demo.data.dao.ClinicaRepository;
import com.example.demo.data.dao.MedicoRepository;
import com.example.demo.data.dao.PacienteRepository;
import com.example.demo.data.entities.Clinica;
import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Paciente;
import com.example.demo.endpoint.message.MessagePaciente;
import com.example.demo.exception.ResourceNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService{

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ClinicaRepository clinicaRepository;
    @Autowired
    private MedicoRepository doctorRepository;

    public MessagePaciente createPaciente(MessagePaciente paciente) {
        Paciente paciente_nuevo = new Paciente();
        
        Optional<Clinica> clinicaOpt = clinicaRepository.findById(paciente.getClinicaId()); 
        Optional<Medico> doctorOpt = doctorRepository.findById(paciente.getMedicoId()); 

        Date date = new Date(paciente.getFechaNacimiento());

        paciente_nuevo.setNombre(paciente.getNombre());
        paciente_nuevo.setClinica(clinicaOpt.get());
        paciente_nuevo.setMedico(doctorOpt.get());
        paciente_nuevo.setApellidoPaterno(paciente.getApellidoPaterno());
        paciente_nuevo.setApellidoMaterno(paciente.getApellidoMaterno());
        paciente_nuevo.setEmail(paciente.getEmail());
        paciente_nuevo.setTelefono(paciente.getTelefono());
        paciente_nuevo.setSexo(paciente.getSexo());
        paciente_nuevo.setFechaNacimiento(date);
        paciente_nuevo.setAlergias(paciente.getAlergias());
        paciente_nuevo.setOperacionesPrevias(paciente.getOperacionesPrevias());
        paciente_nuevo.setEnfermedadesCronicas(paciente.getEnfermedadesCronicas());
        paciente_nuevo.setTratamientosVigentes(paciente.getTratamientosVigentes());
        
        Paciente paciente_creado = pacienteRepository.save(paciente_nuevo);

        MessagePaciente nuevo_paciente = new MessagePaciente();
        nuevo_paciente.setPacienteId(paciente_creado.getPacienteId());
        nuevo_paciente.setNombre(paciente_creado.getNombre());
        nuevo_paciente.setClinicaId(paciente_creado.getClinica().getClinicaId());
        nuevo_paciente.setMedicoId(paciente_creado.getMedico().getMedicoId());
        nuevo_paciente.setApellidoPaterno(paciente_creado.getApellidoPaterno());
        nuevo_paciente.setApellidoMaterno(paciente_creado.getApellidoMaterno());
        nuevo_paciente.setEmail(paciente_creado.getEmail());
        nuevo_paciente.setTelefono(paciente_creado.getTelefono());
        nuevo_paciente.setAlergias(paciente_creado.getAlergias());
        nuevo_paciente.setSexo(paciente_creado.getSexo());
        nuevo_paciente.setOperacionesPrevias(paciente_creado.getOperacionesPrevias());
        nuevo_paciente.setEnfermedadesCronicas(paciente_creado.getEnfermedadesCronicas());
        nuevo_paciente.setTratamientosVigentes(paciente_creado.getTratamientosVigentes());
        nuevo_paciente.setFechaNacimiento(paciente_creado.getFechaNacimiento().toString());
        
        return nuevo_paciente;
        
    }

    public MessagePaciente obtenerPaciente(Integer idPaciente){
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(idPaciente); 
        
        if( pacienteOpt.isEmpty() ){
           throw new ResourceNotFoundException("El medico o el paciente no existe");
        }

        MessagePaciente paciente_obtenido = new MessagePaciente();
        paciente_obtenido.setPacienteId(pacienteOpt.get().getPacienteId());
        paciente_obtenido.setNombre(pacienteOpt.get().getNombre());
        paciente_obtenido.setClinicaId(pacienteOpt.get().getClinica().getClinicaId());
        paciente_obtenido.setMedicoId(pacienteOpt.get().getMedico().getMedicoId());
        paciente_obtenido.setApellidoPaterno(pacienteOpt.get().getApellidoPaterno());
        paciente_obtenido.setApellidoMaterno(pacienteOpt.get().getApellidoMaterno());
        paciente_obtenido.setEmail(pacienteOpt.get().getEmail());
        paciente_obtenido.setTelefono(pacienteOpt.get().getTelefono());
        paciente_obtenido.setAlergias(pacienteOpt.get().getAlergias());
        paciente_obtenido.setSexo(pacienteOpt.get().getSexo());
        paciente_obtenido.setOperacionesPrevias(pacienteOpt.get().getOperacionesPrevias());
        paciente_obtenido.setEnfermedadesCronicas(pacienteOpt.get().getEnfermedadesCronicas());
        paciente_obtenido.setTratamientosVigentes(pacienteOpt.get().getTratamientosVigentes());
        paciente_obtenido.setFechaNacimiento(pacienteOpt.get().getFechaNacimiento().toString());

        return paciente_obtenido;
    }
	

}