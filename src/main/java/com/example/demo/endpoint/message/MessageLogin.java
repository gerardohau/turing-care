package com.example.demo.endpoint.message;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageLogin {

    @NotNull(message = "No puede ser nulo")
    private String username;
    
    @NotNull(message = "No puede ser nulo")
    private String password;
}