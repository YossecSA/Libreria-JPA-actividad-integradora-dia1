package com.egg.persistencia;

import java.util.List;
import com.egg.entidades.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AutorDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    private final EntityManager em = emf.createEntityManager();

    // Método para guardar un nuevo autor
    public void guardaAutor(Autor autor) throws Exception {
        validarAutor(autor);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(autor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); 
            }
            throw e; 
        }
    }

    // Método para listar todos los autores
    public List<Autor> listarTodos() throws Exception {
        return em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
    }

    // Método para obtener un autor por ID
    public Autor obtenerPorId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        return em.find(Autor.class, id);
    }

    // Método para actualizar un autor
    public void actualizarAutor(Autor autor) throws Exception {
        validarAutor(autor); 
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(autor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Método para eliminar un autor
    public void eliminarAutor(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Autor autor = em.find(Autor.class, id);
            if (autor != null) {
                em.remove(autor);
            } else {
                throw new Exception("No se encontró el autor con ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        }
    }

    // Método para buscar autores por nombre
    public List<Autor> buscarPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío.");
        }
        return em.createQuery("SELECT a FROM Autor a WHERE a.nombreAutor LIKE :nombre", Autor.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
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
