package com.libros.init.service;

import com.libros.init.model.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {

    private final List<Libro> libros = new ArrayList<>();

    public LibroService() {
        // Libros de prueba
        libros.add(new Libro("123", "Libro A", "Editorial A", 10.99));
        libros.add(new Libro("456", "Libro B", "Editorial B", 15.99));
        libros.add(new Libro("789", "Libro C", "Editorial C", 20.99));
        libros.add(new Libro("101", "Libro D", "Editorial D", 25.99));
        libros.add(new Libro("102", "Libro E", "Editorial E", 30.99));
    }

    public List<Libro> listarLibros() {
        return libros;
    }

    public Libro obtenerLibroPorIsbn(String isbn) {
        return libros.stream()
                .filter(libro -> libro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public boolean crearLibro(Libro libro) {
        if (libros.stream().anyMatch(l -> l.getIsbn().equals(libro.getIsbn()))) {
            return false;
        }
        libros.add(libro);
        return true;
    }

    public boolean actualizarLibro(String isbn, Libro libroActualizado) {
        return libros.stream()
                .filter(libro -> libro.getIsbn().equals(isbn))
                .findFirst()
                .map(libro -> {
                    int idx = libros.indexOf(libro);
                    libros.set(idx, libroActualizado);
                    return true;
                })
                .orElse(false);
    }

    public boolean eliminarLibro(String isbn) {
        return libros.removeIf(libro -> libro.getIsbn().equals(isbn));
    }
}