package com.example.demo.endpoint.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRegistro {
  
  private Integer idRegistro;

  private String asunto;

  private String descripcion;

  private String fechaCita;

  private Integer idMedico;

  private Integer idPaciente;
}
