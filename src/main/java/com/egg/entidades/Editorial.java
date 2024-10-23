package com.egg.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "editorial")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editoria", nullable = false)
    private Integer idEditoria;

    @Column(name = "nombre_editoria", nullable = true)
    private String nombreEditoria;

    @Column(name = "alta", nullable = false)
    private Boolean alta; // Representa el estado (true/false)

    // Getters y Setters
    public Integer getIdEditoria() {
        return idEditoria;
    }

    public void setIdEditoria(Integer idEditoria) {
        this.idEditoria = idEditoria;
    }

    public String getNombreEditoria() {
        return nombreEditoria;
    }

    public void setNombreEditoria(String nombreEditoria) {
        this.nombreEditoria = nombreEditoria;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
}
