package com.estudiante.gestioncursos.service;

import com.estudiante.gestioncursos.entity.Usuario;
import com.estudiante.gestioncursos.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service de Usuario: concentra la logica de negocio relacionada
 * con usuarios (login, CRUD, reportes) y es el unico punto que
 * los controladores usan para hablar con el repositorio.
 */
@Service
public class UsuarioService {
public List<Usuario> buscarPorRol(String rol) {
        return usuarioRepository.findByRolIgnoreCase(rol);
    }

    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> validarLogin(String nombre, String clave) {
        return usuarioRepository.findByNombreAndClave(nombre, clave);
    }

    public List<Usuario> reportePorRol(String rol) {
        return usuarioRepository.findByRolContainingIgnoreCase(rol);
    }

    public List<Usuario> reportePorNombre(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
