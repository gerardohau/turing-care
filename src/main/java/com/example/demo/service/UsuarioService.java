package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.request.UsuarioRequest;
import com.example.demo.request.loginRequest;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Integer LimiteIntentosLogin = new Integer(3);

	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(Integer idUsuario) {
        Optional<Usuario> optional;
        try{ 
           optional = usuarioRepository.findById(idUsuario);
           return optional.get();
        }catch(NullPointerException | NoSuchElementException e){
            printf("no existe");
        }
	}

	public Usuario createUsuario(@Valid UsuarioRequest request) {
        try{     
            Optional<Usuario> optional = usuarioRepository.findByEmail(request.getEmail());
            if(optional.isPresent()){
                printf("no existe");
            }

        Usuario usuario = new Usuario();
        this.changeRequestUsuarioToUsuario(request,usuario);
        return usuarioRepository.save(usuario);
        }catch(NullPointerException | NoSuchElementException e){
            System.out.println("no existe"); 
        }
	}

	public Usuario updateUsuario(UsuarioRequest request) {
        
        Usuario usuario;
        try{
            Optional<Usuario> optional = usuarioRepository.findById(request.getIdUsuario());
            usuario = optional.get();
        }catch(NullPointerException | NoSuchElementException e){
            System.out.println("no existe"); 
        }
        
        Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getIdUsuario()==usuario.getIdUsuario()){
            changeRequestUsuarioToUsuario(request, usuario);
            return usuarioRepository.save(usuario);
        }else{
            System.out.println("no existe"); 
        }
	}

	public void deleteUsuario() {
      try{
          Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          usuarioRepository.delete(user);
     }catch(NullPointerException | NoSuchElementException e){
        System.out.println("no existe"); 
        }
    }

    public Usuario login(loginRequest request) {
        Usuario usuario;

        try {
            usuario = usuarioRepository.findByUsername(request.getUsername()).get();
        }
        catch(NoSuchElementException e){
            System.out.println("no existe"); 
        }

        if(!usuario.getPassword().equals(request.getPassword())){
            
            Integer intento = (usuario.getIntentoLogin() == null) ? 0 : usuario.getIntentoLogin();
            usuario.setIntentoLogin(intento + 1);
            usuarioRepository.save(usuario);
            boolean validar_intentos = usuario.getIntentoLogin().equals(LimiteIntentosLogin);
            if(validar_intentos) System.out.println("no existe");
            
        }
    
        usuario.setToken(this.crearToken());
        LocalDateTime tiempoIniToken = LocalDateTime.now();
        usuario.setTiempoIniToken(tiempoIniToken);
        usuarioRepository.save(usuario);
        return usuario;
     
        
    }

    //Funciones para formato o seguridad
    public String crearToken(){
        return UUID.randomUUID().toString();
    }

    public LocalDate convertStringTLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,formatter);

        return localDate;
    }

    public void changeRequestUsuarioToUsuario(UsuarioRequest request, Usuario usuario){
            //Validar si es necesario crear un token
            if(usuario.getIdUsuario() == null){
                usuario.setState(true);
                usuario.setToken(this.crearToken());
                LocalDateTime tiempoIniToken = LocalDateTime.now();
                usuario.setTiempoIniToken(tiempoIniToken);
            }else{
                usuario.setState(request.getState());
            }
            
            usuario.setName(request.getName());
            usuario.setUsername(request.getUsername());
            usuario.setEmail(request.getEmail());
            usuario.setPassword(request.getPassword());
            //Convertir string a fecha
            LocalDate birthday =  this.convertStringTLocalDate(request.getBirthday());
            usuario.setBirthday(birthday);
    }

    public void revokeToken (String token){
        Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setToken(null);
        user.setTiempoIniToken(null);
        usuarioRepository.save(user);
    }

}