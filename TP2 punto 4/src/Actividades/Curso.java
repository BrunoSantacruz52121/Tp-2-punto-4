package Actividades;

import Certificacion.Certificable;
import Modelos.Estudiantes;

public class Curso extends Actividad implements Certificable {
    private int horas;

    public Curso(int id, String titulo, int cupoMaximo, int horas) {
        super(id, titulo, cupoMaximo);
        this.horas = horas;
    }

    public int getHoras() {
        return horas;
    }

    @Override
    public double calcularCostoMateriales() {
        return horas * 400.0;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String generarCertificado(Estudiantes estudiante) {
        return "Certificado de aprobación de CURSO emitido por " + ENTIDAD_EMISORA + " a: " + estudiante.getNombre();
    }
}