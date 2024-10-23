package com.egg.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro", nullable = false)
    private Integer idLibro;

    @Column(name = "titulo", nullable = true)
    private String titulo;

    @Column(name = "anio", nullable = true)
    private String anio; // CHAR(4) se mapea como String

    @Column(name = "ejemplares", nullable = true)
    private Integer ejemplares; // TINYINT se mapea como Integer

    @Column(name = "alta", nullable = true)
    private Boolean alta; // TINYINT(1) se mapea como Boolean

    @ManyToOne
    @JoinColumn(name = "id_autor", referencedColumnName = "id_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_editorial", referencedColumnName = "id_editoria")
    private Editorial editorial;

    // Getters y Setters
    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
