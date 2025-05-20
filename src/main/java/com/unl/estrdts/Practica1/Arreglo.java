package com.unl.estrdts.Practica1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Arreglo {
    private int[] arreglo;
    private int longitud;

    public Arreglo(int longitud) {
        this.longitud = longitud;
        this.arreglo = new int[longitud];
    }

    public void setElemento(int indice, int valor) {
        if (indice >= 0 && indice < longitud) {
            arreglo[indice] = valor;
        } else {
            System.out.println("Índice fuera de rango");
        }
    }

    public int getElemento(int indice) {
        if (indice >= 0 && indice < longitud) {
            return arreglo[indice];
        } else {
            System.out.println("Índice fuera de rango");
            return -1;
        }
    }

    public int getLongitud() {
        return longitud;
    }

    public void cargarDesdeArchivo(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < longitud) {
                try {
                    arreglo[index] = Integer.parseInt(line.trim());
                    index++;
                } catch (NumberFormatException e) {
                    System.err.println("Error: El archivo contiene datos no numéricos: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
