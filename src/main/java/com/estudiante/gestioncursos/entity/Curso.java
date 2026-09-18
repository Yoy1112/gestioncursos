package com.estudiante.gestioncursos.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Entidad Curso: entidad de negocio del ejercicio 28.
 */
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precio;

    @NotNull(message = "Las horas son obligatorias")
    @Min(value = 1, message = "Debe durar al menos 1 hora")
    private Integer horas;

    @NotBlank(message = "Debes indicar el nivel")
    private String nivel;

    @NotBlank(message = "El profesor es obligatorio")
    private String profesor;

    @NotBlank(message = "La institución es obligatoria")
    private String institucion;
    private LocalDate fechaInscripcionInicio;
    private LocalDate fechaInscripcionFin;
    private LocalDate fechaInicio;
    private LocalDate fechaCierre;
    private Integer numAlumnos;
    private String modalidad;
    private String descripcion;

    public Curso() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public LocalDate getFechaInscripcionInicio() {
        return fechaInscripcionInicio;
    }

    public void setFechaInscripcionInicio(LocalDate fechaInscripcionInicio) {
        this.fechaInscripcionInicio = fechaInscripcionInicio;
    }

    public LocalDate getFechaInscripcionFin() {
        return fechaInscripcionFin;
    }

    public void setFechaInscripcionFin(LocalDate fechaInscripcionFin) {
        this.fechaInscripcionFin = fechaInscripcionFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Integer getNumAlumnos() {
        return numAlumnos;
    }

    public void setNumAlumnos(Integer numAlumnos) {
        this.numAlumnos = numAlumnos;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
