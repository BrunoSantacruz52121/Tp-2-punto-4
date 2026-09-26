package Actividades;

import Certificacion.Certificable;
import Modelos.Estudiantes;

public class Taller extends Actividad implements Certificable {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 2500.0 : 1200.0;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    @Override
    public String generarCertificado(Estudiantes estudiante) {
        return "Certificado de aprobación de TALLER emitido por " + ENTIDAD_EMISORA + " a: " + estudiante.getNombre();
    }
}