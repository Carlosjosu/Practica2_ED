package com.unl.estrdts.base.controller.DataStruc.List;


//V, K (key, value)
//E (Dato genereico para colecciones)
//T (Dato generico para clases genericas)

public class Node<E> {
    private E data;
    private Node<E> next;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public Node(E data) {
        this.data = data;
        this.next = null;
    }

    public Node(Node<E> next) {
        this.data  = null;
        this.next = next;
    }

    //getters and setters

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }


}
