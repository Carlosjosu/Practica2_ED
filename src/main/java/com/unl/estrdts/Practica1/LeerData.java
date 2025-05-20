package com.unl.estrdts.Practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LeerData {

    public int[] extractData(String filePath) {
        int totalNumbers = countNumbers(filePath);
        if (totalNumbers == 0) {
            System.out.println("El archivo está vacío o no contiene números válidos.");
            return new int[0];
        }

        int[] numbers = new int[totalNumbers];
        int index = 0;

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] parts = line.split(",");
                    for (String part : parts) {

                        numbers[index++] = Integer.parseInt(part.trim());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Línea o parte no válida ignorada: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filePath);
        }

        return numbers;
    }

    private int countNumbers(String filePath) {
        int count = 0;
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                count += parts.length;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filePath);
        }
        return count;
    }

    public void processFile(String filePath) {

        int[] numbers = extractData(filePath);

        if (numbers.length == 0) {
            System.out.println("No se encontraron números válidos en el archivo.");
            return;
        }

        System.out.println("Números encontrados en el archivo:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}