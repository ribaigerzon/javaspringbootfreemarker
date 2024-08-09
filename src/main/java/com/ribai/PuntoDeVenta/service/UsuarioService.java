package com.ribai.PuntoDeVenta.service;

import com.ribai.PuntoDeVenta.logic.Usuario;
import com.ribai.PuntoDeVenta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> authenticate(String nombre, String contrasenia) {
        return usuarioRepository.findByNombreAndContrasenia(nombre, contrasenia);
    }
}