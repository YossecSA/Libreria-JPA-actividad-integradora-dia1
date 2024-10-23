# Proyecto Librería

Este proyecto es una aplicación de gestión de librería que utiliza Java, Hibernate y MySQL para manejar la persistencia de datos. Permite gestionar autores, libros y editoriales.

## Requisitos

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- **Java JDK 11 o superior**
- **MySQL Server**
- **Maven** (opcional, si utilizas Maven para la gestión de dependencias)

## Configuración

## Instalacion

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/tu_usuario/nombre_del_repositorio.git 
    
2. **Navega a la carpeta del proyecto: **
   ```bash
   cd nombre_del_repositorio
   ```
3. **Compila el proyecto: **
   ```bash
   mvn clean install
   ```

### Base de Datos

1. Crea una base de datos en MySQL llamada `db_libreria` (o el nombre que prefieras).

2. En el archivo `persistence.xml`, que se encuentra en `src/main/resources/META-INF/`, cambia los datos de conexión a tu base de datos:

  ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <persistence xmlns="https://jakarta.ee/xml/ns/persistence"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence
                https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
                version="3.0">

       <persistence-unit name="Libreria">
           <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
           <properties>
               <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/db_libreria"/>
               <property name="jakarta.persistence.jdbc.user" value="root"/>
               <property name="jakarta.persistence.jdbc.password" value="contraseña"/> <!-- Cambia por tu contraseña -->
               <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
               <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
               <property name="hibernate.hbm2ddl.auto" value="update"/> <!-- Cambia a 'create' si deseas reiniciar la base de datos -->
               <property name="hibernate.show_sql" value="true"/>
           </properties>
       </persistence-unit>
   </persistence>
  

