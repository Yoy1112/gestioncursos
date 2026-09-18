package com.estudiante.gestioncursos.repository;

import com.estudiante.gestioncursos.entity.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de Curso.
 */
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Reporte parametrizado 1: cursos filtrados por nivel (ej: "Basico", "Avanzado")
    List<Curso> findByNivelIgnoreCase(String nivel);

    // Reporte parametrizado 2: cursos filtrados por modalidad (ej: "Virtual", "Presencial")
    List<Curso> findByModalidadIgnoreCase(String modalidad);
}
    // Reporte parametrizado 3 (extra): cursos con precio menor o igual al indicado
    List<Curso> findByPrecioLessThanEqual(Double precio);
}
