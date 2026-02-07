package com.libros.init.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libros.init.model.Libro;
import com.libros.init.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LibroController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private LibroService libroService;

    private List<Libro> libros;

    @BeforeEach
    void setUp() {
        libros = Arrays.asList(
                new Libro("123", "Libro A", "Editorial A", 10.99),
                new Libro("456", "Libro B", "Editorial B", 15.99)
        );
    }

    @Test
    void listarLibros() throws Exception  {
        when(libroService.listarLibros()).thenReturn(libros);

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void obtenerLibroPorIsbn() throws Exception {
        when(libroService.obtenerLibroPorIsbn("123")).thenReturn(libros.get(0));

        mockMvc.perform(get("/api/libros/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Libro A"));
    }

    @Test
    void crearLibro() throws Exception {
        Libro nuevoLibro = new Libro("789", "Libro C", "Editorial C", 20.99);
        when(libroService.crearLibro(any(Libro.class))).thenReturn(true);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(nuevoLibro)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Libro creado exitosamente."));
    }

    @Test
    void crearLibroDuplicado() throws Exception {
        Libro libroDuplicado = new Libro("123", "Libro A", "Editorial A", 10.99);
        when(libroService.crearLibro(any(Libro.class))).thenReturn(false);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(libroDuplicado)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El ISBN ya existe."));
    }

    @Test
    void actualizarLibro() throws Exception {
        Libro libroActualizado = new Libro("123", "Libro A Actualizado", "Editorial A", 12.99);
        when(libroService.actualizarLibro(eq("123"), any(Libro.class))).thenReturn(true);

        mockMvc.perform(put("/api/libros/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(libroActualizado)))
                .andExpect(status().isOk())
                .andExpect(content().string("Libro actualizado exitosamente."));
    }

    @Test
    void eliminarLibro() throws Exception {
        when(libroService.eliminarLibro("123")).thenReturn(true);

        mockMvc.perform(delete("/api/libros/123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Libro eliminado exitosamente."));
    }
}