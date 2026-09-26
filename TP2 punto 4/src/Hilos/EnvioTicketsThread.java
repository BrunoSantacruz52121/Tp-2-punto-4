package Hilos;

import Actividades.Actividad;
import Modelos.EventoUniversitario;
import Modelos.Inscripcion;

public class EnvioTicketsThread extends Thread {
    private final EventoUniversitario eventoObjetivo;

    public EnvioTicketsThread(EventoUniversitario eventoObjetivo) {
        this.eventoObjetivo = eventoObjetivo;
    }

    @Override
    public void run() {
        System.out.println("\n[THREAD TICKETS] Comenzando transmisión asíncrona de comprobantes...");

        for (Actividad actividadActual : eventoObjetivo.getActividades()) {
            for (Inscripcion registro : actividadActual.getInscripciones()) {
                // Verificación de estado y disponibilidad de ticket
                if (registro.getTicket() != null && "Confirmada".equalsIgnoreCase(registro.getEstado())) {
                    try {
                        // Simulación de demora en red
                        Thread.sleep(1200);
                    } catch (InterruptedException ex) {
                        System.err.println("[THREAD TICKETS] Proceso interrumpido: " + ex.getLocalizedMessage());
                        Thread.currentThread().interrupt();
                        return;
                    }
                    registro.getTicket().enviarTicket();
                }
            }
        }

        System.out.println("[THREAD TICKETS] Transmisión de tickets completada con éxito.\n");
    }
}