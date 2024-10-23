package com.egg.servicio;

import java.util.List;
import com.egg.entidades.Editorial;
import com.egg.persistencia.EditorialDAO;

public class EditorialServicio {
    private final EditorialDAO editorialDAO;

    public EditorialServicio() {
        this.editorialDAO = new EditorialDAO(); 
    }

    // Método para guardar una nueva editorial
    public void crearEditorial(String nombre) throws Exception {
        Editorial editorialNueva = new Editorial();
        editorialNueva.setNombreEditoria(nombre);
        editorialNueva.setAlta(true);
        validarEditorial(editorialNueva); 
        editorialDAO.guardaEditorial(editorialNueva);
    }

    // Método para listar todas las editoriales
    public List<Editorial> listarEditoriales() throws Exception {
        return editorialDAO.listarTodas();
    }

    // Método para obtener una editorial por ID
    public Editorial obtenerEditorialPorId(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        Editorial editorial = editorialDAO.obtenerPorId(id);
        if (editorial == null) {
            throw new Exception("No se encontró una editorial con ID: " + id);
        }
        return editorial;
    }

    // Método para actualizar una editorial
    public void actualizarEditorial(Integer id, String nombre) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }

        Editorial editorialExistente = obtenerEditorialPorId(id); 

        if (editorialExistente != null) {
            editorialExistente.setNombreEditoria(nombre);
            validarEditorial(editorialExistente);
            editorialDAO.actualizarEditorial(editorialExistente);
        } else {
            throw new Exception("No se encontró una editorial con ID: " + id);
        }
    }

    // Método para eliminar una editorial
    public void eliminarEditorial(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("El ID no puede ser nulo.");
        }
        editorialDAO.eliminarEditorial(id);
    }

    // Método para dar de baja a una editorial
    public void darBajaEditorial(Integer id) throws Exception {
        Editorial editorial = editorialDAO.obtenerPorId(id);
        if (editorial != null) {
            editorial.setAlta(false); 
            editorialDAO.actualizarEditorial(editorial);
        }
    }

    private void validarEditorial(Editorial editorial) throws Exception {
        if (editorial == null) {
            throw new Exception("La editorial no puede ser nula.");
        }
        if (editorial.getNombreEditoria() == null || editorial.getNombreEditoria().trim().isEmpty()) {
            throw new Exception("El nombre de la editorial es obligatorio.");
        }
    }

    public void cerrar() {
        editorialDAO.cerrar();
    }
}
