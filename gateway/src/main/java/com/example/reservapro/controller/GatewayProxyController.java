package com.example.reservapro.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class GatewayProxyController {
    private final RestTemplate restTemplate;
    private final Map<String, String> routes;

    public GatewayProxyController(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.routes = Map.ofEntries(
                Map.entry("usuarios", environment.getProperty("reservapro.routes.usuarios", "http://localhost:8081")),
                Map.entry("reservas", environment.getProperty("reservapro.routes.reservas", "http://localhost:8082")),
                Map.entry("pagos", environment.getProperty("reservapro.routes.pagos", "http://localhost:8083"))
        );
    }

    @RequestMapping("/api/{servicio}/**")
    public ResponseEntity<byte[]> proxy(
            @PathVariable String servicio,
            @RequestBody(required = false) byte[] body,
            HttpServletRequest request
    ) {
        String baseUrl = routes.get(servicio);
        if (baseUrl == null) {
            return ResponseEntity.notFound().build();
        }

        String rutaCompleta = request.getRequestURI();
        String prefijo = "/api/" + servicio;
        String rutaDestino = rutaCompleta.length() > prefijo.length() ? rutaCompleta.substring(prefijo.length()) : "/";
        String query = request.getQueryString();
        String urlDestino = baseUrl + rutaDestino + (query == null ? "" : "?" + query);

        HttpHeaders headers = new HttpHeaders();
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            if (!headerName.equalsIgnoreCase("host") && !headerName.equalsIgnoreCase("content-length")) {
                headers.add(headerName, request.getHeader(headerName));
            }
        });

        try {
            HttpEntity<byte[]> entity = new HttpEntity<>(body, headers);
            return restTemplate.exchange(urlDestino, HttpMethod.valueOf(request.getMethod()), entity, byte[].class);
        } catch (RestClientException exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage().getBytes());
        }
    }
}

