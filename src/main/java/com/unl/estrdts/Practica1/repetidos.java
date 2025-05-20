package com.unl.estrdts.Practica1;

import java.util.LinkedList;

public class repetidos {

    public LinkedList<Integer> detectarYGuardarRepetidos(Linkendlist<Integer> list) {
        int size = 0;
        Node<Integer> actual = list.getHead();

        while (actual != null) {
            size++;
            actual = actual.getNext();
        }

        int[] valores = new int[size];
        actual = list.getHead();
        int idx = 0;

        while (actual != null) {
            valores[idx++] = actual.getData();
            actual = actual.getNext();
        }

        LinkedList<Integer> repetidos = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            boolean yaRepetido = false;

            for (Integer r : repetidos) {
                if (r == valores[i]) {
                    yaRepetido = true;
                    break;
                }
            }
            if (yaRepetido)
                continue;

            for (int j = i + 1; j < size; j++) {
                if (valores[i] == valores[j]) {
                    repetidos.add(valores[i]);
                    break;
                }
            }
        }
        return repetidos;
    }
}