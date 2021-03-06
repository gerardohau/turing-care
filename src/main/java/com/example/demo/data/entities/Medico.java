package com.example.demo.data.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "doctor")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_id")
    private Integer medicoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clinica_id")
    Clinica clinica;

    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido_paterno")
    private String apellidoPaterno;

    @Column(name="apellido_materno")
    private String apellidoMaterno;

    @Column(name="curp")
    private String curp;
    
    @Column(name="url_cedula")
    private String urlCedulta;

    @Column(name="url_foto")
    private String urlFoto;

    @Column(name="especialidad")
    private String especialidad;

    @Column(name="status")
    private String status;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

    @OneToMany(mappedBy = "medico")
    private List<Registro> registros;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    Usuario usuario;

    @OneToMany(mappedBy = "medico")
    private List<Paciente> pacientes;
}