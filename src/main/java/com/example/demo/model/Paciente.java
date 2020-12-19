package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Getter
    @Setter
    private Integer paciente_id;

    @Getter
    @Setter
    private Integer doctor_id;

    @Getter
    @Setter
    @Column(name="clinica_id")
    private Integer clinica_id;

    @Getter
    @Setter 
    @Column(name="nombre")
    private String nombre;
    
    @Getter
    @Setter
    @Column(name="apellido_paterno")
    private String apellidoPaterno;

    @Getter
    @Setter
    @Column(name="apellido_materno")
    private String apellidoMaterno;

    @Getter
    @Setter
    @Column(name="email")
    private String email;

    @Getter
    @Setter
    @Column(name="telefono")
    private String telefono;

    @Getter
    @Setter
    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento;

    @Getter
    @Setter
    @Column(name="sexo")
    private String sexo;

    @Getter
    @Setter
    @Column(name="alergias")
    private String alergias;

    @Getter
    @Setter
    @Column(name="operaciones_previas")
    private String operacionesPrevias;

    @Getter
    @Setter
    @Column(name="enfermedades_cronicas")
    private String enfermedadesCronicas;

    @Getter
    @Setter
    @Column(name="tratamientos_vigentes")
    private String tratamientosVigentes;

    @OneToMany(mappedBy = "paciente")
    private List<Registro> registros;

    
}