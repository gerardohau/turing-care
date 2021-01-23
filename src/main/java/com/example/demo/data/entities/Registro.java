package com.example.demo.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Table(name = "registro")
@Entity
@Getter
@Setter
public class Registro{
  
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  @Id
  @Column(name = "registro_id")
  private Integer registroId;

  @Column(name="asunto")
  private String asunto;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "sintomas")
  private String sintomas;

  @Column(name = "medicamento_recetado")
  private String medicamentoRecetado;

  @Column(name = "observaciones")
  private String observaciones;

  @Column(name = "tipo_tratamiento")
  private String tipoTratamiento;

  @Column(name = "seguimiento_tratamiento")
  private String seguimientoTratamiento;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_registro")
  private Date fechaRegistro;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_actualizacion")
  private Date fechaActualizacion;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "fecha_cita")
  private Date fechaCita;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="doctor_id")
  Medico medico;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="paciente_id")
  Paciente paciente;
}