package com.example.demo.endpoint.message;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL )
public class MessageRegistro {
  
  private Integer idRegistro;

  private String asunto;

  private String descripcion;

  private String fechaCita;

  private String fechaCreado;

  private String fechaActualizado;

  private Integer idMedico;

  private Integer idPaciente;

  private MessagePaciente paciente;

  private MessageMedico medico;

  private String sintomas;

  private String medicamentoRecetado;

  private String observaciones;

  private String tipoTratamiento;

  private String seguimientoTratamiento;
}
