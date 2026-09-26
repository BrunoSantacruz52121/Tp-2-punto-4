package Modelos;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado; // "Pendiente", "Confirmada"
    private Estudiantes estudiante;
    private TicketDeAcceso ticket;

    public Inscripcion(Estudiantes estudiante) {
        this.fecha = LocalDate.now();
        this.estado = "Pendiente";
        this.estudiante = estudiante;
    }

    public String getEstado() { return estado; }
    public Estudiantes getEstudiante() { return estudiante; }

    public void confirmarInscripcion() {
        this.estado = "Confirmada";
        this.ticket = new TicketDeAcceso();
    }

    public TicketDeAcceso getTicket() { return ticket; }
    public class TicketDeAcceso implements Serializable {
        private String codigoTicket;

        public TicketDeAcceso() {
            this.codigoTicket = "TKT-" + estudiante.getLegajo() + "-" + (int)(Math.random() * 1000);
        }

        public void enviarTicket() {
            System.out.println("-> [ENVIANDO TICKET] Ticket " + codigoTicket + " enviado a " + estudiante.getNombre());
        }

        public String getCodigoTicket() { return codigoTicket; }
    }
}