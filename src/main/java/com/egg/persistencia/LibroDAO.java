package com.egg.persistencia;

import java.util.List;
import com.egg.entidades.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class LibroDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    private final EntityManager em = emf.createEntityManager();

    // Método para guardar un nuevo libro
    public void guardaLibro(Libro libro) throws Exception {
        validarLibro(libro); 
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); 
            }
            throw e; 
        }
    }

    // Método para listar todos los libros
    public List<Libro> listarTodos() throws Exception {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }

    // Método para obtener un libro por ID
    public Libro obtenerPorId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        return em.find(Libro.class, id);
    }

    // Método para actualizar un libro
    public void actualizarLibro(Libro libro) throws Exception {
        validarLibro(libro); 
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        }
    }

    // Método para eliminar un libro
    public void eliminarLibro(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Libro libro = em.find(Libro.class, id);
            if (libro != null) {
                em.remove(libro);
            } else {
                throw new Exception("No se encontró el libro con ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Método para buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) throws Exception {
        return em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class)
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
    }

    // Método para buscar libros por autor
    public List<Libro> buscarPorAutor(String nombreAutor) throws Exception {
        return em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombreAutor LIKE :nombre", Libro.class)
                .setParameter("nombre", "%" + nombreAutor + "%")
                .getResultList();
    }

    // Método para buscar libros por editorial
    public List<Libro> buscarPorEditorial(String nombreEditorial) throws Exception {
        return em.createQuery("SELECT l FROM Libro l JOIN l.editorial e WHERE e.nombre LIKE :nombre", Libro.class)
                .setParameter("nombre", "%" + nombreEditorial + "%")
                .getResultList();
    }
    
    // Método para validar libro
    private void validarLibro(Libro libro) throws Exception {
        if (libro == null) {
            throw new Exception("El libro no puede ser nulo.");
        }
        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new Exception("El título del libro es obligatorio.");
        }
    }

    // Método para cerrar el EntityManager
    public void cerrar() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
