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
public class MessageMedico {

    private Integer medicoId;

    private Integer clinicaId;

    private String nombre;
    
    private String apellidoPaterno;

    private String apellidoMaterno;

    private String curp;
    
    private String urlCedulta;

    private String urlFoto;

    private String especialidad;

    private String status;
    
    private String email;
   
    private String telefono;
}