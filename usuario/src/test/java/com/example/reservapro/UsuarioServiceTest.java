package com.example.reservapro;

import com.example.reservapro.Model.EstadoUsuario;
import com.example.reservapro.Model.RolUsuario;
import com.example.reservapro.Model.Usuario;
import com.example.reservapro.repository.UsuarioRepository;
import com.example.reservapro.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void guardarDebePersistirEntidad() {
        Usuario entidad = new Usuario();
        entidad.setNombre("Camila"); entidad.setEmail("camila@reservapro.cl"); entidad.setPassword("123456");
        when(repository.existsByEmail(entidad.getEmail())).thenReturn(false);
        when(repository.save(entidad)).thenReturn(entidad);

        Usuario resultado = service.guardar(entidad);

        assertSame(entidad, resultado);
        verify(repository).save(entidad);
    }


    @Test
    void guardarUsuarioConEmailExistenteDebeLanzarExcepcion() {

        Usuario usuario = new Usuario();
        usuario.setNombre("Luciano");
        usuario.setApellido("Castro");
        usuario.setEmail("luciano@gmail.com");
        usuario.setPassword("123456");
        usuario.setTelefono("987654321");
        usuario.setDireccion("Av. Libertador 123");
        usuario.setRol(RolUsuario.CLIENTE);
        usuario.setEstado(EstadoUsuario.ACTIVO);

        when(repository.existsByEmail("luciano@gmail.com"))
                .thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.guardar(usuario)
        );

        System.out.println(exception.getMessage());

        assertEquals("Ya existe un usuario con ese email", exception.getMessage());
    }


}

