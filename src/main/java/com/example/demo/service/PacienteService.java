package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.data.dao.PacienteRepository;
import com.example.demo.data.dao.UsuarioRepository;
import com.example.demo.data.dao.ClinicaRepository;
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

    public Paciente createPaciente(MessagePaciente paciente) {
        Paciente paciente_nuevo = new Paciente();
        
        Optional<Clinica> clinicaOpt = clinicaRepository.findById(paciente.getClinicaId()); 
        paciente_nuevo.setNombre(paciente.getNombre());
        paciente_nuevo.setClinica(clinicaOpt.get());
        paciente_nuevo.setApellidoPaterno(paciente.getApellidoPaterno());
        paciente_nuevo.setApellidoMaterno(paciente.getApellidoMaterno());
        paciente_nuevo.setEmail(paciente.getEmail());
        

        PacienteRepository.save(paciente_nuevo);
        
        return paciente_nuevo;
    }

	

}