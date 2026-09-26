# Trabajo Práctico N° 2

**Materia:** Paradigmas de Programación  
**Legajo:** 52121  

---

## 📌 Descripción General
Este proyecto consiste en el desarrollo de un sistema en **Java** para la gestión integral de inscripciones y administración de actividades en un evento universitario (`EventoUniversitario`). Se aplican conceptos avanzados de la Programación Orientada a Objetos, como Herencia, Polimorfismo, Generics, Concurrencia y Persistencia de datos.

> **Nota sobre la ejecución:** Toda la entrada de datos inicial (carga de estudiantes, salas y actividades) ya se encuentra **predeterminada y precargada en el código** dentro de la clase `Main` para facilitar las pruebas rápidas de ejecución.

---
**`Actividades` (o `actividades`):**
   * **`Actividad.java`**: Clase abstracta base que define los atributos comunes (ID, título, cupo máximo) y la estructura de inscripción.
   * **`Charla.java`**, **`Taller.java`**, **`Curso.java`**: Subclases concretas que heredan de `Actividad`, especificando sus propios costos de materiales y particularidades de cupo o dictado.

**`Certificacion`:**
   * **`Certificable.java`**: Interfaz que define el contrato `generarCertificado(...)` para aquellas actividades que otorgan acreditación formal al estudiante.

**`Excepciones`:**
   * **`CupoExcedidoExcepcion.java`**: Excepción personalizada (*checked exception*) que se dispara cuando un estudiante intenta inscribirse en una actividad que alcanzó su capacidad máxima (`cupoMaximo`).

**`Hilos`:**
   * **`EnvioTicketsThread.java`**: Clase que extiende de `Thread` para realizar el envío y procesamiento asíncrono/concurrente de tickets sin congelar la ejecución del hilo principal de la aplicación.

---
