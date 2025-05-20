package com.unl.estrdts.Practica1;

import java.io.IOException;
import java.util.List;
import com.unl.estrdts.Practica1.excepcion.ListEmptyException;
import com.unl.estrdts.Practica1.Linkendlist;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Linkendlist<Integer> list = new Linkendlist<>();
        repetidos repetidosObj = new repetidos();
        String filePath = "data.txt";

        long[] tiempos = new long[3];

        for (int i = 1; i <= 3; i++) {
            long tiempoInicio = System.currentTimeMillis();

            try {
                list.loadFromFile(filePath);
                System.out.println("Datos cargados desde el archivo.");
            } catch (IOException e) {
                System.out.println("Error al cargar el archivo: " + e.getMessage());
            }

            long totalTiempo = System.currentTimeMillis() - tiempoInicio;
            tiempos[i - 1] = totalTiempo;
            System.out.println("Ejecución " + i + ":\t" + totalTiempo + " ms");
        }

        List<Integer> elementosRepetidos = repetidosObj.detectarYGuardarRepetidos(list);
        System.out.println("Elementos repetidos: " + elementosRepetidos);
        System.out.println("Número de elementos repetidos: " + elementosRepetidos.size());

        System.out.println("\nTabla comparativa de tiempos:");
        System.out.println("Ejecución\tTiempo (ms)");
        for (int i = 0; i < tiempos.length; i++) {
            System.out.println((i + 1) + "\t\t" + tiempos[i] + " ms");
        }
    }
}