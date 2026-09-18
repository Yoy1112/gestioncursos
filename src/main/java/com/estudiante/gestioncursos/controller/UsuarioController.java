package com.estudiante.gestioncursos.controller;

import com.estudiante.gestioncursos.entity.Usuario;
import com.estudiante.gestioncursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller MVC de Usuario. Recibe las solicitudes, delega en el
 * Service y retorna el nombre de la plantilla Thymeleaf que debe
 * renderizarse, enviando los datos necesarios a traves del Model.
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar (Read)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/list";
    }

    // Formulario para crear
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    // Formulario para editar (Update - parte 1)
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    // Crear o actualizar (Create / Update - parte 2)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/usuarios";
    }

    // Eliminar (Delete)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    // Reportes parametrizados
    @GetMapping("/reportes")
    public String reportes(@RequestParam(required = false) String rol,
                            @RequestParam(required = false) String nombre,
                            Model model) {
        if (rol != null && !rol.isBlank()) {
            model.addAttribute("resultados", usuarioService.reportePorRol(rol));
            model.addAttribute("filtroUsado", "Rol: " + rol);
        } else if (nombre != null && !nombre.isBlank()) {
            model.addAttribute("resultados", usuarioService.reportePorNombre(nombre));
            model.addAttribute("filtroUsado", "Nombre contiene: " + nombre);
        }
        return "usuarios/reportes";
    }
}
