package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
  
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  @Id
  @Column(name = "usuario_id")
  private Integer registroId;

  @Column(name="usuario")
  private String usuario;

  @Column(name = "password")
  private String password;

  @Column(name="token")
  private String token;

  @Column(name = "tiempoIniToken")
  private LocalDateTime tiempoIniToken;

  @Column(name = "intengoslogin")
  private Integer intentoLogin = 0;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="doctor_id")
  Medico medico;
  
}
