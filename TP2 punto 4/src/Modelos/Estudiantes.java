package Modelos;

import java.io.Serializable;

public class Estudiantes implements Serializable {
    private String legajo;
    private String nombre;

    public Estudiantes(String legajo, String nombre) {
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public String getLegajo() { return legajo; }

    public String getNombre() {
        return nombre;
    }
}