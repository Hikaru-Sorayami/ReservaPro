package com.example.reservapro.client;

import com.example.reservapro.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service")
public interface UsuarioClient {
    @GetMapping("/usuarios/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable Long id);
}
