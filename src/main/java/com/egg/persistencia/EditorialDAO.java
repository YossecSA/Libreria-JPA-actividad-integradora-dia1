package com.egg.persistencia;

import java.util.List;
import com.egg.entidades.Editorial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EditorialDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    private final EntityManager em = emf.createEntityManager();

    // Método para guardar una nueva editorial
    public void guardaEditorial(Editorial editorial) throws Exception {
        validarEditorial(editorial); 
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(editorial);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    // Método para listar todas las editoriales
    public List<Editorial> listarTodas() throws Exception {
        return em.createQuery("SELECT e FROM Editorial e", Editorial.class).getResultList();
    }

    // Método para obtener una editorial por ID
    public Editorial obtenerPorId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        return em.find(Editorial.class, id);
    }

    // Método para actualizar una editorial
    public void actualizarEditorial(Editorial editorial) throws Exception {
        validarEditorial(editorial); 
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(editorial);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        }
    }

    // Método para eliminar una editorial
    public void eliminarEditorial(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Editorial editorial = em.find(Editorial.class, id);
            if (editorial != null) {
                em.remove(editorial);
            } else {
                throw new Exception("No se encontró la editorial con ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e; 
        }
    }

    // Método para validar editorial
    private void validarEditorial(Editorial editorial) throws Exception {
        if (editorial == null) {
            throw new Exception("La editorial no puede ser nula.");
        }
        if (editorial.getNombreEditoria() == null || editorial.getNombreEditoria().trim().isEmpty()) {
            throw new Exception("El nombre de la editorial es obligatorio.");
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
