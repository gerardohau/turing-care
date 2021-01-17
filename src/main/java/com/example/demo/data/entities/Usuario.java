package com.example.demo.data.entities;

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
  @Column(name = "user_id")
  private Integer usuarioId;

  @Column(name="usuario")
  private String usuario;

  @Column(name = "password")
  private String password;

  @Column(name="token")
  private String token;

  @Column(name="role")
  private String role;

  @Column(name = "tiempo_init_token")
  private LocalDateTime tiempoIniToken;

  @Column(name = "intentos_login")
  private Integer intentoLogin = 0;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="doctor_id")
  Medico medico;
  
}