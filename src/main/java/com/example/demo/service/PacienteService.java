package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.Date;

import javax.validation.Valid;

import com.example.demo.data.dao.PacienteRepository;
import com.example.demo.data.dao.UsuarioRepository;
import com.example.demo.data.dao.ClinicaRepository;
import com.example.demo.data.dao.MedicoRepository;

import com.example.demo.data.entities.*;
import com.example.demo.endpoint.message.*;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService{

    @Autowired
    private PacienteRepository PacienteRepository;
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
        
        Paciente paciente_creado = PacienteRepository.save(paciente_nuevo);

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

	

}