package com.egg.servicio;

import java.util.List;
import com.egg.entidades.Autor;
import com.egg.persistencia.AutorDAO;

public class AutorServicio {
    private final AutorDAO autorDAO;

    public AutorServicio() {
        this.autorDAO = new AutorDAO(); 
    }

    // Método para guardar un nuevo autor
    public void crearAutor(String nombre) {
        try {
            Autor autorNuevo = new Autor();
            autorNuevo.setNombreAutor(nombre);
            autorNuevo.setAlta(true);
            validarAutor(autorNuevo);
            autorDAO.guardaAutor(autorNuevo);
        } catch (Exception e) {
            System.out.println(e.toString() + ": No se guardó el nuevo autor de manera correcta.");
        }
    }

    // Método para listar todos los autores
    public List<Autor> listarAutores() throws Exception {
        return autorDAO.listarTodos();
    }

    // Método para obtener un autor por ID
    public Autor obtenerAutorPorId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        Autor autor = autorDAO.obtenerPorId(id);
        if (autor == null) {
            throw new Exception("No se encontró un autor con ID: " + id);
        }
        return autor;
    }

    // Método para actualizar un autor
    public void actualizarAutor(Integer id, String nombre) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
    
        Autor autorExistente = obtenerAutorPorId(id);
        if (autorExistente != null) {
            autorExistente.setNombreAutor(nombre);
            validarAutor(autorExistente);
            autorDAO.actualizarAutor(autorExistente);
        } else {
            throw new Exception("No se encontró un autor con ID: " + id);
        }
    }

    // Método para eliminar un autor
    public void eliminarAutor(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        autorDAO.eliminarAutor(id);
    }

    // Método para dar de baja a un autor
    public void darBajaAutor(Integer id) throws Exception {
        Autor autor = obtenerAutorPorId(id); 
        autor.setAlta(false); 
        autorDAO.actualizarAutor(autor); 
    }

    // Método para buscar autores por nombre
    public List<Autor> buscarPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío.");
        }
        
        List<Autor> autores = autorDAO.buscarPorNombre(nombre);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores con el nombre: " + nombre);
        }
        return autores;
    }

    // Método para validar autor
    private void validarAutor(Autor autor) throws Exception {
        if (autor == null) {
            throw new Exception("El autor no puede ser nulo.");
        }
        if (autor.getNombreAutor() == null || autor.getNombreAutor().trim().isEmpty()) {
            throw new Exception("El nombre del autor es obligatorio.");
        }
    }

    public void cerrar() {
        autorDAO.cerrar();
    }
}
