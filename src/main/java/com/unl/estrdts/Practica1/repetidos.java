package com.unl.estrdts.Practica1;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class repetidos {

    public List<Integer> detectarYGuardarRepetidos(Linkendlist<Integer> list) {
        HashSet<Integer> elementosUnicos = new HashSet<>();
        List<Integer> elementosRepetidos = new ArrayList<>();

        Node<Integer> actual = list.getHead();
        while (actual != null) {
            int valor = actual.getData();
            if (!elementosUnicos.add(valor)) {
                if (!elementosRepetidos.contains(valor)) {
                    elementosRepetidos.add(valor);
                }
            }
            actual = actual.getNext();
        }

        return elementosRepetidos;
    }

}