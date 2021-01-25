package com.example.demo.endpoint.message;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL )
public class MessagePaciente {

    private Integer pacienteId;

    private Integer clinicaId;

    private Integer medicoId;

    private String nombre;
    
    private String apellidoPaterno;

    private String apellidoMaterno;

    private String fechaNacimiento;

    private String email;
    
    private String sexo;

    private String telefono;

    private String alergias;

    private String operacionesPrevias;

    private String enfermedadesCronicas;

    private String tratamientosVigentes;

}