package com.ribai.PuntoDeVenta.repository;

import com.ribai.PuntoDeVenta.logic.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreAndContrasenia(String nombre, String contrasenia);
    Optional<Usuario> findByNombre(String nombre);
}

