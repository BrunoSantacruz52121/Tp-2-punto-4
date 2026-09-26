package Modelos;

import Actividades.Actividad;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private final List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    // Constructor de copia
    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
    }

    // Getters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public List<Actividad> getActividades() { return actividades; }
    public static int getCantidadEventos() { return cantidadEventos; }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public double calcularCostoEstimado() {
        return gratuito ? 0.0 : costoBase;
    }

    // Filtrado genérico con foreach explícito
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Actividad item : actividades) {
            if (tipo.isInstance(item)) {
                resultado.add(tipo.cast(item));
            }
        }
        return resultado;
    }

    // Cálculo de costos con wildcard de suma acumulativa
    public double calcularCostoMateriales(List<? extends Actividad> listaActividades) {
        double acumulado = 0.0;
        for (Actividad item : listaActividades) {
            acumulado += item.calcularCostoMateriales();
        }
        return acumulado;
    }

    public void mostrarDatos() {
        System.out.println("=== Informes del Evento: " + titulo + " (Código: " + id + ") ===");
        if (sala != null) {
            System.out.println("Ubicación: " + sala.getNombre());
        }
        System.out.println("Total de actividades registradas: " + actividades.size());
    }

    // Persistencia en archivo
    public boolean persistirEvento() {
        String archivo = id + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
            return true;
        } catch (IOException ex) {
            System.err.println("Fallo al guardar objeto serializable: " + ex.getMessage());
            return false;
        }
    }

    // Recuperación con multi-catch
    public static EventoUniversitario recuperarEvento(String id) {
        String archivo = id + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (EventoUniversitario) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.err.println("No se encontró el registro persistido: " + ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error al reconstruir el evento desde archivo: " + ex.getMessage());
        }
        return null;
    }
}