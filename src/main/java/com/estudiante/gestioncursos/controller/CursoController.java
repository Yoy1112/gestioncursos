package com.estudiante.gestioncursos.controller;

import com.estudiante.gestioncursos.entity.Curso;
import com.estudiante.gestioncursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
/**
 * Controller MVC de Curso (entidad del ejercicio 28).
 */
@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "cursos/list";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursos/form";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado: " + id));
        model.addAttribute("curso", curso);
        return "cursos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("curso") Curso curso, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cursos/form";
        }
        cursoService.guardar(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return "redirect:/cursos";
    }

    @GetMapping("/reportes")
    public String reportes(@RequestParam(required = false) String nivel,
                            @RequestParam(required = false) String modalidad,
                            @RequestParam(required = false) Double precioMaximo,
                            Model model) {
        if (nivel != null && !nivel.isBlank()) {
            model.addAttribute("resultados", cursoService.reportePorNivel(nivel));
            model.addAttribute("filtroUsado", "Nivel: " + nivel);
        } else if (modalidad != null && !modalidad.isBlank()) {
            model.addAttribute("resultados", cursoService.reportePorModalidad(modalidad));
            model.addAttribute("filtroUsado", "Modalidad: " + modalidad);
        } else if (precioMaximo != null) {
            model.addAttribute("resultados", cursoService.reportePorPrecioMaximo(precioMaximo));
            model.addAttribute("filtroUsado", "Precio maximo: " + precioMaximo);
        }
        return "cursos/reportes";
    }
}
