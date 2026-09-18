package com.estudiante.gestioncursos.service;

import com.estudiante.gestioncursos.entity.Curso;
import com.estudiante.gestioncursos.repository.CursoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service de Curso: logica de negocio de la entidad del ejercicio 28.
 */
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    public List<Curso> buscarPorNivel(String nivel) {
        return cursoRepository.findByNivelIgnoreCase(nivel);
    }

    public List<Curso> buscarPorModalidad(String modalidad) {
        return cursoRepository.findByModalidadIgnoreCase(modalidad);
    }
}

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> reportePorNivel(String nivel) {
        return cursoRepository.findByNivelIgnoreCase(nivel);
    }

    public List<Curso> reportePorModalidad(String modalidad) {
        return cursoRepository.findByModalidadIgnoreCase(modalidad);
    }

    public List<Curso> reportePorPrecioMaximo(Double precio) {
        return cursoRepository.findByPrecioLessThanEqual(precio);
    }
}
