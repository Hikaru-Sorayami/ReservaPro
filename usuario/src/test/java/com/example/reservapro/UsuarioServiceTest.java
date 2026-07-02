package com.example.reservapro;

import com.example.reservapro.Model.Usuario;
import com.example.reservapro.repository.UsuarioRepository;
import com.example.reservapro.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertSame;
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
}

