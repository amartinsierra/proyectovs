package com.libros.init.model;

public class Libro {

    private String isbn;
    private String titulo;
    private String editorial;
    private double precio;

    public Libro() {
    }

    public Libro(String isbn, String titulo, String editorial, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editorial = editorial;
        this.precio = precio;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}