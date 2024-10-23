package com.egg.menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.egg.entidades.Autor;
import com.egg.entidades.Editorial;
import com.egg.entidades.Libro;
import com.egg.servicio.AutorServicio;
import com.egg.servicio.EditorialServicio;
import com.egg.servicio.LibroServicio;

public class Menu {
    private final Scanner scanner;
    private final AutorServicio autorServicio;
    private final EditorialServicio editorialServicio;
    private final LibroServicio libroServicio;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.autorServicio = new AutorServicio();
        this.editorialServicio = new EditorialServicio();
        this.libroServicio = new LibroServicio();
    }

    public void mostrarMenu() throws Exception {
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Administrar Autores");
            System.out.println("2. Administrar Editoriales");
            System.out.println("3. Administrar Libros");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    administrarAutores();
                    break;
                case 2:
                    administrarEditoriales();
                    break;
                case 3:
                    administrarLibros();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void administrarAutores() {
        int opcion = 0;
        do {
            System.out.println("\n--- Administrar Autores ---");
            System.out.println("1. Crear Autor");
            System.out.println("2. Listar Autores");
            System.out.println("3. Actualizar Autor");
            System.out.println("4. Eliminar Autor");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            // Manejo de entrada para evitar InputMismatchException
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        crearAutor();
                        break;
                    case 2:
                        listarAutores();
                        break;
                    case 3:
                        actualizarAutor();
                        break;
                    case 4:
                        eliminarAutor();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                scanner.nextLine(); // Limpiar el buffer
            }
        } while (opcion != 0);
    }


    private void crearAutor() {
        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.next();
        // Lógica para crear un autor
        try {
            autorServicio.crearAutor(nombre);
            System.out.println("Autor creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el autor: " + e.getMessage());
        }
    }

    private void listarAutores() {
        try {
            List<Autor> autores = autorServicio.listarAutores();
            System.out.println("Lista de Autores:");
            for (Autor autor : autores) {
                System.out.println(autor.getIdAutor() + ": " + autor.getNombreAutor());
            }
        } catch (Exception e) {
            System.out.println("Error al listar autores: " + e.getMessage());
        }
    }

    private void actualizarAutor() {
        System.out.print("Ingrese el ID del autor a actualizar: ");
        
        try {
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            
            System.out.print("Ingrese el nuevo nombre del autor: ");
            String nombre = scanner.nextLine();
    
            autorServicio.actualizarAutor(id, nombre);
            System.out.println("Autor actualizado correctamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero para el ID.");
            scanner.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Ocurrió un error al actualizar el autor: " + e.getMessage());
        }
    }

    private void eliminarAutor() {
        System.out.print("Ingrese el ID del autor a eliminar: ");
        Integer id = scanner.nextInt();
        try {
            autorServicio.eliminarAutor(id);
            System.out.println("Autor eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el autor: " + e.getMessage());
        }
    }

    private void administrarEditoriales() {
        int opcion;
        do {
            System.out.println("\n--- Administrar Editoriales ---");
            System.out.println("1. Crear Editorial");
            System.out.println("2. Listar Editoriales");
            System.out.println("3. Actualizar Editorial");
            System.out.println("4. Eliminar Editorial");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearEditorial();
                    break;
                case 2:
                    listarEditoriales();
                    break;
                case 3:
                    actualizarEditorial();
                    break;
                case 4:
                    eliminarEditorial();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void crearEditorial() {
        System.out.print("Ingrese el nombre de la editorial: ");
        String nombre = scanner.next();
        // Lógica para crear una editorial
        try {
            editorialServicio.crearEditorial(nombre);
            System.out.println("Editorial creada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear la editorial: " + e.getMessage());
        }
    }

    private void listarEditoriales() {
        try {
            List<Editorial> editoriales = editorialServicio.listarEditoriales();
            System.out.println("Lista de Editoriales:");
            for (Editorial editorial : editoriales) {
                System.out.println(editorial.getIdEditoria() + ": " + editorial.getNombreEditoria());
            }
        } catch (Exception e) {
            System.out.println("Error al listar editoriales: " + e.getMessage());
        }
    }

    private void actualizarEditorial() {
        Integer id = null; // Inicializar id como null
        boolean valido = false;
    
        while (!valido) {
            System.out.print("Ingrese el ID de la editorial a actualizar: ");
            try {
                id = scanner.nextInt(); // Leer ID
                scanner.nextLine(); // Limpiar el buffer
                valido = true; // Si se llega aquí, el ID es válido
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para el ID.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    
        // Leer el nuevo nombre de la editorial
        System.out.print("Ingrese el nuevo nombre de la editorial: ");
        String nuevoNombre = scanner.nextLine(); // Usar nextLine para capturar espacios
    
        // Intentar actualizar la editorial
        try {
            editorialServicio.actualizarEditorial(id, nuevoNombre);
            System.out.println("Editorial actualizada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar la editorial: " + e.getMessage());
        }
    }
    

    private void eliminarEditorial() {
        System.out.print("Ingrese el ID de la editorial a eliminar: ");
        Integer id = scanner.nextInt();
        try {
            editorialServicio.eliminarEditorial(id);
            System.out.println("Editorial eliminada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar la editorial: " + e.getMessage());
        }
    }

    private void administrarLibros() throws Exception {
        int opcion;
        do {
            System.out.println("\n--- Administrar Libros ---");
            System.out.println("1. Crear Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Actualizar Libro");
            System.out.println("4. Eliminar Libro");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void crearLibro() throws Exception {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.next();
        System.out.print("Ingrese el año del libro: ");
        String anio = scanner.next();
        System.out.print("Ingrese la cantidad de ejemplares: ");
        Integer ejemplares = scanner.nextInt();
        
        // Pedir ID del autor
        System.out.print("Ingrese el ID del autor: ");
        Integer idAutor = scanner.nextInt();
        Autor autor = autorServicio.obtenerAutorPorId(idAutor); 

        // Pedir ID de la editorial
        System.out.print("Ingrese el ID de la editorial: ");
        Integer idEditorial = scanner.nextInt();
        Editorial editorial = editorialServicio.obtenerEditorialPorId(idEditorial);

        // Lógica para crear el libro
        try {
            libroServicio.crearLibro(titulo, anio, ejemplares, autor, editorial);
            System.out.println("Libro creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el libro: " + e.getMessage());
        }
    }

    private void listarLibros() {
        try {
            List<Libro> libros = libroServicio.listarLibros();
            System.out.println("Lista de Libros:");
            for (Libro libro : libros) {
                System.out.println(libro.getIdLibro() + ": " + libro.getTitulo());
            }
        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }
    }

    private void actualizarLibro() {
        System.out.print("Ingrese el ID del libro a actualizar: ");
        Integer id = null;
        boolean valido = false;
    
        // Validar que se ingrese un ID válido
        while (!valido) {
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                valido = true; // ID válido
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para el ID.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    
        // Pedir el nuevo título del libro
        System.out.print("Ingrese el nuevo título del libro: ");
        String nuevoTitulo = scanner.nextLine();
    
        // Pedir el año del libro
        System.out.print("Ingrese el año del libro: ");
        String nuevoAnio = scanner.nextLine();
    
        // Pedir la cantidad de ejemplares
        System.out.print("Ingrese la cantidad de ejemplares: ");
        Integer ejemplares = null;
        valido = false;
        while (!valido) {
            try {
                ejemplares = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                valido = true; // Ejemplares válido
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para la cantidad de ejemplares.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    
        // Pedir el ID del autor
        System.out.print("Ingrese el ID del autor: ");
        Integer autorId = scanner.nextInt();
        Autor autor = null;
    
        // Intentar obtener el autor por ID
        try {
            autor = autorServicio.obtenerAutorPorId(autorId);
            if (autor == null) {
                throw new Exception("No se encontró un autor con ID: " + autorId);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el autor: " + e.getMessage());
            return; // Salir del método si hay un error
        }
    
        // Pedir el ID de la editorial
        System.out.print("Ingrese el ID de la editorial: ");
        Integer editorialId = scanner.nextInt();
        Editorial editorial = null;
    
        // Intentar obtener la editorial por ID
        try {
            editorial = editorialServicio.obtenerEditorialPorId(editorialId);
            if (editorial == null) {
                throw new Exception("No se encontró una editorial con ID: " + editorialId);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la editorial: " + e.getMessage());
            return; // Salir del método si hay un error
        }
    
        // Intentar actualizar el libro
        try {
            libroServicio.actualizarLibro(id, nuevoTitulo, nuevoAnio, ejemplares, autor, editorial);
            System.out.println("Libro actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el libro: " + e.getMessage());
        }
    
    }

    private void eliminarLibro() {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        Integer id = scanner.nextInt();
        try {
            libroServicio.eliminarLibro(id);
            System.out.println("Libro eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }
}
