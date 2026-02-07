package com.libros.init.controller;

import com.libros.init.service.LibroService;
import com.libros.init.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> obtenerLibroPorIsbn(@PathVariable String isbn) {
        Libro libro = libroService.obtenerLibroPorIsbn(isbn);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> crearLibro(@RequestBody Libro libro) {
        boolean creado = libroService.crearLibro(libro);
        if (creado) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Libro creado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ISBN ya existe.");
        }
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<String> actualizarLibro(@PathVariable String isbn, @RequestBody Libro libro) {
        boolean actualizado = libroService.actualizarLibro(isbn, libro);
        if (actualizado) {
            return ResponseEntity.ok("Libro actualizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado.");
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> eliminarLibro(@PathVariable String isbn) {
        boolean eliminado = libroService.eliminarLibro(isbn);
        if (eliminado) {
            return ResponseEntity.ok("Libro eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro no encontrado.");
        }
    }
}