package com.estudiante.gestioncursos.repository;

import com.estudiante.gestioncursos.entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Usuario. Spring Data JPA genera automaticamente la
 * implementacion de estos metodos con solo declarar la firma, siguiendo
 * las convenciones de nombres (findBy...).
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Para el login: busca un usuario por nombre y clave exactos
    Optional<Usuario> findByNombreAndClave(String nombre, String clave);
// Reporte 1: Buscar usuarios por rol
    List<Usuario> findByRolIgnoreCase(String rol);

    // Reporte 2: Buscar usuarios por nombre
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    // Reporte parametrizado 1: usuarios filtrados por rol
    List<Usuario> findByRolContainingIgnoreCase(String rol);

    // Reporte parametrizado 2: usuarios filtrados por nombre (busqueda parcial)
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}
