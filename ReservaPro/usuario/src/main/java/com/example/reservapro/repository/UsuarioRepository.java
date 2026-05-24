package com.example.reservapro.repository;

import com.example.reservapro.Model.EstadoUsuario;
import com.example.reservapro.Model.RolUsuario;
import com.example.reservapro.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByRol(RolUsuario rol);

    List<Usuario> findByEstado(EstadoUsuario estado);
}
