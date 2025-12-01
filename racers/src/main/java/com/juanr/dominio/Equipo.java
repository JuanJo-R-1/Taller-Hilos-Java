package com.juanr.dominio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Equipo {
    private String nombreEquipo;
    private ExecutorService organizador; // El "Thread Pool" del equipo

    public Equipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
        // 💡 IMPORTANTE: Un SingleThreadExecutor garantiza orden secuencial [FIFO]
        // Es perfecto para un relevo
        this.organizador = Executors.newSingleThreadExecutor();
    }

    public void comenzarCarrera() {
        System.out.println("El equipo " + nombreEquipo + " esta listo en la línea de salida.");
        
        // Creamos las tareas (Runnables)
        Corredor c1 = new Corredor(nombreEquipo + "-1");
        Corredor c2 = new Corredor(nombreEquipo + "-2");
        Corredor c3 = new Corredor(nombreEquipo + "-3");

        // "Encolamos" a los corredores. 
        // El ejecutor se encarga de hacerlos correr uno tras otro automáticamente.
        organizador.execute(c1);
        organizador.execute(c2);
        organizador.execute(c3);

        // Np acepta más corredores, pero termina los pendientes
        organizador.shutdown();
    }
}