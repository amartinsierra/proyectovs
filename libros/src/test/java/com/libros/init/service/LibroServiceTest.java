package com.libros.init.service;

import com.libros.init.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibroServiceTest {

    private LibroService libroService;

    @BeforeEach
    void setUp() {
        libroService = new LibroService();
    }

    

    @Test
    void obtenerLibroPorIsbn() {
        Libro libro = libroService.obtenerLibroPorIsbn("123");
        assertNotNull(libro);
        assertEquals("Libro A", libro.getTitulo());
    }

    @Test
    void crearLibro() {
        Libro nuevoLibro = new Libro("999", "Libro F", "Editorial F", 40.99);
        boolean creado = libroService.crearLibro(nuevoLibro);
        assertTrue(creado);
        assertEquals(6, libroService.listarLibros().size());
    }

    @Test
    void crearLibroDuplicado() {
        Libro libroDuplicado = new Libro("123", "Libro A", "Editorial A", 10.99);
        boolean creado = libroService.crearLibro(libroDuplicado);
        assertFalse(creado);
    }

    @Test
    void actualizarLibro() {
        Libro libroActualizado = new Libro("123", "Libro A Actualizado", "Editorial A", 12.99);
        boolean actualizado = libroService.actualizarLibro("123", libroActualizado);
        assertTrue(actualizado);
        assertEquals("Libro A Actualizado", libroService.obtenerLibroPorIsbn("123").getTitulo());
    }

    @Test
    void actualizarLibroNoExistente() {
        Libro libroActualizado = new Libro("999", "Libro F", "Editorial F", 40.99);
        boolean actualizado = libroService.actualizarLibro("999", libroActualizado);
        assertFalse(actualizado);
    }

    @Test
    void eliminarLibro() {
        boolean eliminado = libroService.eliminarLibro("123");
        assertTrue(eliminado);
        assertNull(libroService.obtenerLibroPorIsbn("123"));
    }

    @Test
    void eliminarLibroNoExistente() {
        boolean eliminado = libroService.eliminarLibro("999");
        assertFalse(eliminado);
    }
    @Test
    void listarLibros() {
        List<Libro> libros = libroService.listarLibros();
        assertEquals(5, libros.size());
    }
}