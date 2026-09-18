package com.estudiante.gestioncursos.controller;

import com.estudiante.gestioncursos.entity.Usuario;
import com.estudiante.gestioncursos.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller de autenticacion. Maneja el flujo:
 * navegador -> Controller -> Service -> Repository -> BD -> Service -> Controller/Model -> Thymeleaf -> HTML
 */
@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String nombre,
                                 @RequestParam String clave,
                                 HttpSession session,
                                 Model model) {
        Optional<Usuario> usuario = usuarioService.validarLogin(nombre, clave);
        if (usuario.isPresent()) {
            session.setAttribute("usuarioLogueado", usuario.get());
            return "redirect:/home";
        }
        model.addAttribute("error", "Usuario o clave incorrectos");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // --- Recuperacion de clave (version simplificada) ---
    // NOTA PARA EL ESTUDIANTE: aqui falta conectar un envio real de correo
    // (por ejemplo con JavaMailSender de Spring). Por ahora esta pantalla
    // muestra la clave en lugar de enviarla por email, para que el flujo
    // completo (formulario -> busqueda -> respuesta) ya quede armado y
    // solo tengas que enchufar el envio de correo si te alcanza el tiempo.
    @GetMapping("/recuperar-clave")
    public String mostrarRecuperarClave() {
        return "recuperar-clave";
    }

    @PostMapping("/recuperar-clave")
    public String procesarRecuperarClave(@RequestParam String nombre, Model model) {
        Optional<Usuario> usuario = usuarioService.listarTodos().stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
        if (usuario.isPresent()) {
            model.addAttribute("mensaje",
                    "TODO: aqui se debe enviar la clave por correo. Por ahora, tu clave registrada es: "
                            + usuario.get().getClave());
        } else {
            model.addAttribute("mensaje", "No se encontro un usuario con ese nombre.");
        }
        return "recuperar-clave";
    }
}
