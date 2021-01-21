package com.example.demo.endpoint.message;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageMedico {

    @NotNull(message = "El password es nulo")
    @Size(min = 8, max = 12, message 
      = "El password debe tener entre12 y 50 caracteres")
    @NotEmpty(message = "El título es vacío")
    private String name;

    private Integer clinicaId;
}