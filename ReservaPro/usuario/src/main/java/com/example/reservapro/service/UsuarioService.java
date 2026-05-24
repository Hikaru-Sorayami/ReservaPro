package com.example.reservapro.service;

import com.example.reservapro.Model.EstadoUsuario;
import com.example.reservapro.Model.RolUsuario;
import com.example.reservapro.Model.Usuario;
import com.example.reservapro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> listarPorRol(RolUsuario rol) {
        return usuarioRepository.findByRol(rol);
    }

    public List<Usuario> listarPorEstado(EstadoUsuario estado) {
        return usuarioRepository.findByEstado(estado);
    }

    public Usuario actualizar(Long id, Usuario datosUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuarioRepository.findByEmail(datosUsuario.getEmail())
                .filter(usuarioExistente -> !usuarioExistente.getId().equals(id))
                .ifPresent(usuarioExistente -> {
                    throw new IllegalArgumentException("Ya existe un usuario con ese email");
                });

        usuario.setNombre(datosUsuario.getNombre());
        usuario.setApellido(datosUsuario.getApellido());
        usuario.setEmail(datosUsuario.getEmail());
        usuario.setPassword(datosUsuario.getPassword());
        usuario.setTelefono(datosUsuario.getTelefono());
        usuario.setDireccion(datosUsuario.getDireccion());
        usuario.setRol(datosUsuario.getRol());
        usuario.setEstado(datosUsuario.getEstado());

        return usuarioRepository.save(usuario);
    }

    public Usuario cambiarEstado(Long id, EstadoUsuario estado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuario.setEstado(estado);
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales invalidas"));

        if (!usuario.getPassword().equals(password) || usuario.getEstado() != EstadoUsuario.ACTIVO) {
            throw new IllegalArgumentException("Credenciales invalidas");
        }

        return usuario;
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
