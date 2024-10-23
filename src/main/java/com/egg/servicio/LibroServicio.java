package com.egg.servicio;

import java.util.List;
import com.egg.entidades.Libro;
import com.egg.persistencia.LibroDAO;
import com.egg.entidades.Autor;
import com.egg.entidades.Editorial;

public class LibroServicio {
    private final LibroDAO libroDAO;

    public LibroServicio() {
        this.libroDAO = new LibroDAO();
    }

    // Método para guardar un nuevo libro
    public void crearLibro(String titulo, String anio, Integer ejemplares, Autor autor, Editorial editorial) throws Exception {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAnio(anio);
        nuevoLibro.setEjemplares(ejemplares);
        nuevoLibro.setAutor(autor);
        nuevoLibro.setEditorial(editorial);
        nuevoLibro.setAlta(true); 

        validarLibro(nuevoLibro);

        libroDAO.guardaLibro(nuevoLibro);
    }

    // Método para listar todos los libros
    public List<Libro> listarLibros() throws Exception {
        return libroDAO.listarTodos();
    }

    // Método para obtener un libro por ID
    public Libro obtenerLibroPorId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID del libro no puede ser nulo");
        }

        Libro libro = libroDAO.obtenerPorId(id);
        if (libro == null) {
            throw new Exception("No se encontró el libro con ID: " + id);
        }
        return libro;
    }

    // Método para actualizar un libro
    public void actualizarLibro(Integer id, String titulo, String anio, Integer ejemplares, Autor autor, Editorial editorial) throws Exception {
        // Validar el ID del libro
        if (id == null) {
            throw new Exception("El ID del libro no puede ser nulo.");
        }

        // Obtener el libro existente
        Libro libroExistente = obtenerLibroPorId(id);

        // Si el libro existe, actualiza sus campos
        if (libroExistente != null) {
            libroExistente.setTitulo(titulo);
            libroExistente.setAnio(anio);
            libroExistente.setEjemplares(ejemplares);
            libroExistente.setAutor(autor);
            libroExistente.setEditorial(editorial);

            validarLibro(libroExistente);
            
            libroDAO.actualizarLibro(libroExistente);
        }
    }

    // Método para eliminar un libro
    public void eliminarLibro(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID del libro no puede ser nulo.");
        }
        libroDAO.eliminarLibro(id);
    }

    // Método para dar de baja a un libro
    public void darBajaLibro(Integer id) throws Exception {
        Libro libro = obtenerLibroPorId(id);
        if (libro != null) {
            libro.setAlta(false);
            libroDAO.actualizarLibro(libro);
        }
    }

    // Método para buscar un libro por ID
    public Libro buscarPorId(Integer id) throws Exception {
        return obtenerLibroPorId(id); 
    }

    // Método para buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("El título no puede estar vacío");
        }
        
        List<Libro> libros = libroDAO.buscarPorTitulo(titulo);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con el título: " + titulo);
        }
        return libros;
    }

    // Método para buscar libros por autor
    public List<Libro> buscarPorAutor(String nombreAutor) throws Exception {
        if (nombreAutor == null || nombreAutor.trim().isEmpty()) {
            throw new Exception("El nombre del autor no puede estar vacío");
        }
        
        List<Libro> libros = libroDAO.buscarPorAutor(nombreAutor);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros para el autor: " + nombreAutor);
        }
        return libros;
    }

    // Método para buscar libros por editorial
    public List<Libro> buscarPorEditorial(String nombreEditorial) throws Exception {
        if (nombreEditorial == null || nombreEditorial.trim().isEmpty()) {
            throw new Exception("El nombre de la editorial no puede estar vacío");
        }
        
        List<Libro> libros = libroDAO.buscarPorEditorial(nombreEditorial);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros de la editorial: " + nombreEditorial);
        }
        return libros;
    }

    // Método para validar el libro
    private void validarLibro(Libro libro) throws Exception {
        if (libro == null) {
            throw new Exception("El libro no puede ser nulo.");
        }
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new Exception("El título del libro es obligatorio.");
        }
        if (libro.getAnio() == null || libro.getAnio().trim().isEmpty()) {
            throw new Exception("El año del libro es obligatorio.");
        }
        if (libro.getEjemplares() == null || libro.getEjemplares() < 0) {
            throw new Exception("El número de ejemplares debe ser 0 o mayor.");
        }
        if (libro.getAutor() == null) {
            throw new Exception("El autor del libro es obligatorio.");
        }
        if (libro.getEditorial() == null) {
            throw new Exception("La editorial del libro es obligatoria.");
        }
    }

    public void cerrar() {
        libroDAO.cerrar();
    }
}
