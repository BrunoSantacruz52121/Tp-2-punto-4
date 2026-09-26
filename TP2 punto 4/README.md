# TP2 - Paradigmas de Programación (UTN - FRM)
Este repositorio contiene la solución completa para el Trabajo Práctico N° 2 de la materia. El objetivo principal fue escalar el sistema de gestión de eventos universitarios aplicando de la Unidad 2.

### Lo que se implementó:
- **Excepciones propias:** Creamos `CupoExcedidoExcepcion` para controlar de forma limpia cuando una actividad se queda sin espacio al intentar inscribir un alumno.
- **Interfaces:** Sumamos la interfaz `Certificable` para que solo los Talleres y Cursos emitan certificados de participación
- **Genéricos y Wildcards:** Implementamos métodos parametrizados como `filtrarActividadesPorTipo` y `calcularCostoMateriales` para manejar listas tipadas de actividades de forma flexible.
- **Persistencia:** Guardado y recuperación binaria del estado del evento (`EventoUniversitario`) mediante serialización de objetos en disco.
- **Concurrencia (Hilos):** Armamos `EnvioTicketsThread` para procesar el envío de tickets en segundo plano sin congelar la ejecución ni la pantalla principal.

---
**Legajo:** 52131  
**Materia:** Paradigmas de Programación - UTN Facultad Regional Mendoza
