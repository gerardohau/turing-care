package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroRequest {
  
  private Integer idRegistro;

  private String asunto;

  private String descripcion;

  private String fechaCita;

  private Integer idMedico;

  private Integer idPaciente;
}
