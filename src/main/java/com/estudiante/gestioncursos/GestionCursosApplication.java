package com.estudiante.gestioncursos;

import com.estudiante.gestioncursos.entity.Usuario;
import com.estudiante.gestioncursos.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Clase principal. Arranca el servidor embebido de Spring Boot.
 * Al iniciar, si no existe ningun usuario en la base de datos,
 * crea un usuario administrador por defecto para poder entrar
 * a la aplicacion la primera vez.
 *
 * Usuario por defecto: admin / admin123
 */
@SpringBootApplication
public class GestionCursosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionCursosApplication.class, args);
    }

    @Bean
    public CommandLineRunner inicializarDatos(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario admin = new Usuario();
                admin.setNombre("admin");
                admin.setClave("admin123");
                admin.setRol("ADMIN");
                usuarioRepository.save(admin);
                System.out.println("=== Usuario administrador creado: admin / admin123 ===");
            }
        };
    }
}
