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
public class MessageUsuario {
       
    private Integer idUsuario;

    @NotNull(message = "El password es nulo")
    @Size(min = 8, max = 12, message 
      = "El password debe tener entre12 y 50 caracteres")
    @NotEmpty(message = "El título es vacío")
    private String password;

    @NotNull(message = "El username es nulo")
    @Size(min = 5, max = 10, message 
      = "El username debe tener entre 5 y 10 caracteres")
    @NotEmpty(message = "El username es vacío")
    private String username;

    private String token;

    private String role;
}