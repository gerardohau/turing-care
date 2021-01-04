package com.example.demo.model;

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
@Table(name = "doctor")
public class Medico {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
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
    @Column(name="curp")
    private String curp;
    
    @Getter
    @Setter
    @Column(name="urlCedulta")
    private String urlCedulta;

    @Getter
    @Setter
    @Column(name="url_foto")
    private String urlFoto;

    @Getter
    @Setter
    @Column(name="especialidad")
    private String especialidad;

    @Getter
    @Setter
    @Column(name="status")
    private String status;

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
    @Column(name="user_id")
    private String userId;

    @OneToMany(mappedBy = "medico")
    private List<Registro> registros;
    
}