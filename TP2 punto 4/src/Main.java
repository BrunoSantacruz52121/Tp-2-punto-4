
import Actividades.Actividad;
import Actividades.Charla;
import Actividades.Curso;
import Actividades.Taller;
import Certificacion.Certificable;
import Excepciones.CupoExcedidoExcepcion;
import Hilos.EnvioTicketsThread;
import Modelos.Estudiantes;
import Modelos.EventoUniversitario;
import Modelos.Inscripcion;
import Modelos.Sala;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("┌──────────────────────────────────────────────────┐");
        System.out.println("│     SISTEMA DE GESTIÓN DE EVENTOS - UTN FRM      │");
        System.out.println("└──────────────────────────────────────────────────┘\n");

        // Instanciación de estudiantes
        Estudiantes alumnoA = new Estudiantes("10", "Eduardo Nose");
        Estudiantes alumnoB = new Estudiantes("11", "Carlos Roberto");
        Estudiantes alumnoC = new Estudiantes("12", "Martin Santineli");

        // Configuración del espacio y evento
        Sala aulaMagna = new Sala(1, "Aula Magna");
        EventoUniversitario eventoAnual = new EventoUniversitario("EVT-2026", "Congreso de Tecnología UTN", 50.0, false);
        eventoAnual.asignarSala(aulaMagna);

        // Definición de actividades académicas
        Charla charlaIA = new Charla(101, "Introducción a IA", 2, "Dr. Pérez");
        Taller tallerGit = new Taller(102, "Taller de Git y GitHub", 1, true);
        Curso cursoJava = new Curso(103, "Curso Intensivo Java", 5, 20);

        // Registro masivo de actividades en el evento
        eventoAnual.getActividades().addAll(Arrays.asList(charlaIA, tallerGit, cursoJava));

        System.out.println("[ 1. GESTIÓN DE EXCEPCIONES Y CONTROL DE CUPOS ]");
        try {
            System.out.println(">>> Inscribiendo primer alumno en Taller (Capacidad máxima: 1)...");
            Inscripcion inscripcionTaller1 = tallerGit.inscribir(alumnoA);
            inscripcionTaller1.confirmarInscripcion();
            System.out.println("✓ Inscripción confirmada satisfactoriamente.");

            System.out.println(">>> Intentando registrar segundo alumno en el mismo Taller...");
            Inscripcion inscripcionTaller2 = tallerGit.inscribir(alumnoB);
        } catch (CupoExcedidoExcepcion ex) {
            System.err.println("CAPTURADO: " + ex.getMessage());
        } finally {
            System.out.println("Proceso de validación de cupo completado.\n");
        }

        try {
            Inscripcion regCharla = charlaIA.inscribir(alumnoA);
            regCharla.confirmarInscripcion();

            Inscripcion regCursoB = cursoJava.inscribir(alumnoB);
            regCursoB.confirmarInscripcion();

            Inscripcion regCursoC = cursoJava.inscribir(alumnoC); // Queda pendiente
        } catch (CupoExcedidoExcepcion ex) {
            System.err.println("Fallo al registrar: " + ex.getMessage());
        }

        System.out.println("\n[ 2. FILTRADO CON GENERICS Y CÁLCULO DE COSTOS ]");
        List<Curso> cursosRegistrados = eventoAnual.filtrarActividadesPorTipo(Curso.class);
        List<Taller> talleresRegistrados = eventoAnual.filtrarActividadesPorTipo(Taller.class);

        System.out.println("Total de cursos almacenados: " + cursosRegistrados.size());
        System.out.println("Total de talleres almacenados: " + talleresRegistrados.size());

        double presupuestoMateriales = eventoAnual.calcularCostoMateriales(cursosRegistrados);
        System.out.println("Costo global de insumos para Cursos: $" + presupuestoMateriales);

        System.out.println("\n[ 3. GENERACIÓN DE CERTIFICACIONES ]");
        eventoAnual.getActividades().forEach(actividadActual -> {
            if (actividadActual instanceof Certificable) {
                Certificable entidadCertificable = (Certificable) actividadActual;
                for (Inscripcion ins : actividadActual.getInscripciones()) {
                    System.out.println(entidadCertificable.generarCertificado(ins.getEstudiante()));
                }
            } else {
                System.out.println("Atención: La actividad '" + actividadActual.getTitulo() + "' (" + actividadActual.getTipo() + ") no otorga certificado.");
            }
        });

        System.out.println("\n[ 4. SERIALIZACIÓN Y PERSISTENCIA ]");
        if (eventoAnual.persistirEvento()) {
            System.out.println("Datos guardados en almacenamiento local correctamente.");
        }

        EventoUniversitario eventoCargado = EventoUniversitario.recuperarEvento("EVT-2026");
        if (eventoCargado != null) {
            System.out.println("Instancia recuperada exitosamente: " + eventoCargado.getTitulo());
        }

        System.out.println("\n[ 5. PROCESAMIENTO ASÍNCRONO DE TICKETS ]");
        EnvioTicketsThread hiloProcesoTickets = new EnvioTicketsThread(eventoAnual);
        hiloProcesoTickets.start();

        int paso = 1;
        while (paso <= 3) {
            System.out.println("[THREAD MAIN] Ejecutando interfaz principal... (" + paso + "/3)");
            try {
                Thread.sleep(800);
            } catch (InterruptedException err) {
                Thread.currentThread().interrupt();
            }
            paso++;
        }
    }
}