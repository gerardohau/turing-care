package com.example.demo.model;

import java.util.Date;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "paciente_id")
    private Integer paciente_id;

    @Column(name="clinica_id")
    private Integer clinica_id;
 
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido_paterno")
    private String apellidoPaterno;

    @Column(name="apellido_materno")
    private String apellidoMaterno;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name="sexo")
    private String sexo;

    @Column(name="alergias")
    private String alergias;

    @Column(name="operaciones_previas")
    private String operacionesPrevias;

    @Column(name="enfermedades_cronicas")
    private String enfermedadesCronicas;

    @Column(name="tratamientos_vigentes")
    private String tratamientosVigentes;

    @OneToMany(mappedBy = "paciente")
    private List<Registro> registros;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    Medico medico;
}