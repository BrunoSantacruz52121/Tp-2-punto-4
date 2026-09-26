package Certificacion;

import Modelos.Estudiantes;

public interface Certificable {
    String ENTIDAD_EMISORA = "UTN - Facultad Regional Mendoza";

    String generarCertificado(Estudiantes estudiante);
}