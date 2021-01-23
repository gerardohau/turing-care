package com.example.demo.service;

import java.util.Optional;

import com.example.demo.data.dao.ClinicaRepository;
import com.example.demo.data.dao.MedicoRepository;
import com.example.demo.data.entities.Clinica;
import com.example.demo.data.entities.Medico;
import com.example.demo.endpoint.message.MessageMedico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService{

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ClinicaRepository clinicaRepository;

    public MessageMedico createMedico(MessageMedico medico) {
        Medico medico_nuevo = new Medico();
        
        Optional<Clinica> clinicaOpt = clinicaRepository.findById(medico.getClinicaId()); 
        medico_nuevo.setNombre(medico.getNombre());
        medico_nuevo.setClinica(clinicaOpt.get());
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

	

}