package Actividades;

import Excepciones.CupoExcedidoExcepcion;
import Modelos.Estudiantes;
import Modelos.Inscripcion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO = 5;

    private final List<Inscripcion> listaInscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.listaInscripciones = new ArrayList<>();
    }
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getCupoMaximo() { return cupoMaximo; }
    public List<Inscripcion> getInscripciones() { return listaInscripciones; }

    public Inscripcion inscribir(Estudiantes estudiante) throws CupoExcedidoExcepcion {
        if (listaInscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoExcepcion("Capacidad máxima alcanzada para la actividad: " + titulo);
        }
        Inscripcion nuevaInscripcion = new Inscripcion(estudiante);
        listaInscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }

    public void mostrarInscripciones() {
        System.out.println("=== Detalle de Inscritos: " + titulo + " ===");
        listaInscripciones.forEach(registro ->
                System.out.println("• Alumno: " + registro.getEstudiante() + " | Estado actual: " + registro.getEstado())
        );
    }

    public final void mostrarIdentificacion() {
        System.out.println("Clave: [" + id + "] | Título: " + titulo + " (Categoría: " + getTipo() + ")");
    }

    public abstract double calcularCostoMateriales();
    public abstract String getTipo();
}