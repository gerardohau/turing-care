package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.data.dao.MedicoRepository;
import com.example.demo.data.dao.UsuarioRepository;
import com.example.demo.data.dao.ClinicaRepository;
import com.example.demo.data.entities.*;
import com.example.demo.endpoint.message.*;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MedicoService{

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ClinicaRepository clinicaRepository;

    public Medico createMedico(MessageMedico medico) {
        Medico medico_nuevo = new Medico();
        
        Optional<Clinica> clinicaOpt = clinicaRepository.findById(medico.getClinicaId()); 
        medico_nuevo.setNombre(medico.getName());
        medico_nuevo.setClinica(clinicaOpt.get());

        return medico_nuevo;
    }

	

}