package com.example.demo.data.entities;

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
@Getter
@Setter
@Table(name = "clinica")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clinica_id")
    private Integer clinicaId;

    @Column(name="nombre")
    private String nombre;
    
    @Column(name="calle")
    private String calle;

    @Column(name="numero")
    private String numero;

    @Column(name="codigo_postal")
    private String codigoPostal;
    
    @Column(name="cruzamiento")
    private String cruzamiento;

    @Column(name="colonia")
    private String colonia;

    @Column(name="municipio")
    private String municipio;

    @Column(name="estado")
    private String estado;

    @Column(name="pais")
    private String pais;

    @Column(name="telefono")
    private String telefono;

    @Column(name="user_id")
    private String userId;

    @OneToMany(mappedBy = "clinica")
    private List<Medico> medicos;

}