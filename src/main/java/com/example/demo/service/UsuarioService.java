package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.data.dao.MedicoRepository;
import com.example.demo.data.dao.UsuarioRepository;
import com.example.demo.data.entities.Medico;
import com.example.demo.data.entities.Usuario;
import com.example.demo.endpoint.message.*;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoService medicoService;

    private final Integer LimiteIntentosLogin = new Integer(3);

	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(Integer idUsuario) {
        Optional<Usuario> optional;
        try{ 
           optional = usuarioRepository.findById(idUsuario);
           Usuario usuario = new Usuario();
           List<Medico> medico = usuario.getMedicos();
            if( !medico.isEmpty() ){
                MessageMedico mMedico = new MessageMedico();
                mMedico.setMedicoId( medico.get(0).getMedicoId()  );
                mMedico.setEmail( medico.get(0).getEmail()  );
                mMedico.setStatus( medico.get(0).getStatus()  );
                mMedico.setCurp( medico.get(0).getCurp()  );
                mMedico.setEspecialidad( medico.get(0).getEspecialidad()  );
                mMedico.setTelefono( medico.get(0).getTelefono()  );
                mMedico.setNombre( medico.get(0).getNombre() );
                mMedico.setApellidoPaterno(medico.get(0).getApellidoPaterno());
                mMedico.setApellidoMaterno(medico.get(0).getApellidoMaterno());
                
                usuario.setMessageMedico(mMedico);
            }
           return usuario;
        }catch(NullPointerException | NoSuchElementException e){
            throw new ResourceNotFoundException("El usuario no se ha encontrado");
        }
	}

	public Usuario createUsuario(@Valid MessageUsuario request) {
        try{     
            Optional<Usuario> optional = usuarioRepository.findByUsuario( request.getUsername() != null ? request.getUsername():"" );
            if(optional.isPresent()){
                throw new ResourceNotFoundException("El usuario ya existe.");
            }

            Usuario usuario = new Usuario();
            this.changeRequestUsuarioToUsuario(request,usuario);
            usuarioRepository.save(usuario);

            return usuario;
        }catch(NullPointerException | NoSuchElementException e){
            throw new ResourceNotFoundException("El usuario ya existe");
        }
	}

	public Usuario updateUsuario(MessageUsuario request) {
        
        Usuario usuario = new Usuario();
        try{
            Optional<Usuario> optional = usuarioRepository.findById(request.getIdUsuario());
            usuario = optional.get();
        }catch(NullPointerException | NoSuchElementException e){
            throw new ResourceNotFoundException("El usuario no existe");
        }
        
        Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getUsuarioId() ==usuario.getUsuarioId() ){

            changeRequestUsuarioToUsuario(request, usuario);
            usuarioRepository.save(usuario);

            return usuario;
        }else{
            throw new ResourceNotFoundException("El usuario no existe");
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

    public Usuario login(MessageLogin request) {
        Usuario usuario = new Usuario();

        try {
            usuario = usuarioRepository.findByUsuario(request.getUsername()).get();
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
        List<Medico> medico = usuario.getMedicos();
        if( !medico.isEmpty() ){
            MessageMedico mMedico = new MessageMedico();
            mMedico.setMedicoId( medico.get(0).getMedicoId()  );
            mMedico.setEmail( medico.get(0).getEmail()  );
            mMedico.setStatus( medico.get(0).getStatus()  );
            mMedico.setCurp( medico.get(0).getCurp()  );
            mMedico.setEspecialidad( medico.get(0).getEspecialidad()  );
            mMedico.setTelefono( medico.get(0).getTelefono()  );
            mMedico.setNombre( medico.get(0).getNombre() );
            mMedico.setApellidoPaterno(medico.get(0).getApellidoPaterno());
            mMedico.setApellidoMaterno(medico.get(0).getApellidoMaterno());
            
            usuario.setMessageMedico(mMedico);
        }
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

    public void changeRequestUsuarioToUsuario(MessageUsuario request, Usuario usuario){
            //Validar si es necesario crear un token
        if(usuario.getUsuarioId() == null) {
            usuario.setToken(this.crearToken());
            LocalDateTime tiempoIniToken = LocalDateTime.now();
            usuario.setTiempoIniToken(tiempoIniToken);
        }
            
        usuario.setUsuario(request.getUsername());
        usuario.setRole(request.getRole());
        usuario.setPassword(request.getPassword());
    }

    public void revokeToken (String token){
        Usuario user = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setToken(null);
        user.setTiempoIniToken(null);
        usuarioRepository.save(user);
    }

}