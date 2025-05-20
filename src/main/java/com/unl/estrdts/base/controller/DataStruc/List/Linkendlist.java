package com.unl.estrdts.base.controller.DataStruc.List;

import com.unl.estrdts.base.controller.excepcion.ListEmptyException;

public class Linkendlist<E> {
    private Node<E> head;
    private Node<E> last;
    private Integer length;

    // getter and setter
    public Integer getLength() {
        return length;
    }

    // public void setLength(Integer lenth) {
    //     this.length = lenth;
    // }

    public Linkendlist() {
        this.head = null;
        this.last = null;
        this.length = 0;
    }

    public Boolean isEmpty() {
        return head == null || length == 0;
    }

    private Node<E> getNode(Integer pos) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Indice Fuera de rango");
            // System.out.println("lista vacia");
            // return null;
        } else if (pos < 0 || pos >= length) {
            throw new ListEmptyException("Indice Fuera de rango");
            // System.out.println("Fuera de rango");
            // return null;
        } else if (pos == 0) {
            return head;
        } else if ((length.intValue()-1) == pos.intValue()) {
            return last;
        } else {
            Node<E> search = head;
            Integer cont = 0;
            while (cont < pos) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    private E getDattaFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista esta vacia");
        } else {
            return head.getData();
        }
    }

    private E getDataLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista esta vacia");
        } else {
            return last.getData();
        }
    }

    public E get(Integer pos) throws ListEmptyException {
        return getNode(pos).getData();
    }

    private void addFirst(E data) {
        if (isEmpty()) {
            Node<E> aux = new Node<>(data);
            head = aux;
            last = aux;
        } else {
            Node<E> head_old = head;
            Node<E> aux = new Node<>(data, head_old);
            head = aux;
        }
        length++;
    }

    private void addLast(E data) {
        if (isEmpty()) {
            addFirst(data);
        } else {
            Node<E> aux = new Node<>(data);
            last.setNext(aux);
            last = aux;
            length++;
        }
    }

    public void add(E data, Integer pos) throws ListEmptyException {
        if (pos == 0) {
            addFirst(data);
        } else if (length.intValue() == pos.intValue()) {
            addLast(data);
        } else {
            Node<E> search_preview = getNode(pos - 1);
            Node<E> search = getNode(pos);
            Node<E> aux = new Node<>(data, search);
            search_preview.setNext(aux);
            length++;
        }
    }

    public void add(E data) {
        addLast(data);
    }

    public String print() {
        if (isEmpty()) {
            return "La lista esta vacia";

        } else {
            // head = null
            StringBuilder resp = new StringBuilder();
            Node<E> help = head;
            while (help != null) {
                // resp += help.getData() + " -> ;
                resp.append(help.getData()).append(" -> ");
                help = help.getNext();
            }
            resp.append("\n");
            return resp.toString();
        }
    }

    public void update(E data, Integer pos) throws ListEmptyException {
        getNode(pos).setData(data);
    }

    public void clear() {
        head = null;
        last = null;
        length = 0;

    }

    protected E deleteFirst () throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista se encuentra vacia");
        } else {
            E element = head.getData();
            Node<E> aux = head.getNext();
            head = aux;
            if (length.intValue() == 1) {
                last = null;
            }
            length--;
            return element;
        }
    }

    // protected E deleteLast() throws ListEmptyException {
    //     if (isEmpty()) {
    //         throw new ListEmptyException("La lista se encuentra vacia");
    //     } else {
    //         E element = last.getData();
    //         Node<E> aux = getNode(length - 2);
    //         if (aux == null) {
    //             last = null;
    //             if (length == 2) {
    //                 last = head;
    //             } else {
    //                 head = null;
    //             }   
    //         } else {
    //             last = null;
    //             last = aux;
    //             last.setNext(null);
    //         }
    //         length --;
    //         return element;
    //     }
    // }

    protected E deleteLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista se encuentra vacía");
        } else if (length == 1) {
            E element = head.getData();
            head = null;
            last = null;
            length = 0;
            return element;
        } else {
            Node<E> penultimo = getNode(length - 2);
            E element = last.getData();
            penultimo.setNext(null);
            last = penultimo;
            length--;
            return element;
        }
    }
    

    public E delete(Integer pos) throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista se encuentra vacia");
        } else if (pos < 0 || pos >= length) {
            throw new ListEmptyException("Indice fuera de rango");
        } else if (pos == 0) {
            return deleteFirst();
        } else if ((length - 1) == pos) {
            return deleteLast();
        } else {
            Node<E> preview = getNode(pos - 1);
            Node<E> actualy = getNode(pos);
            E element = actualy.getData();
            Node<E> next = actualy.getNext();
            actualy = null;
            preview.setNext(next);
            length--;
            return element;
        }
    }

    // public E delete(Integer pos) throws ListEmptyException {
    //     if (isEmpty()) {
    //         throw new ListEmptyException("La lista se encuentra vacia");
    //     } else if (pos < 0 || pos >= length) {
    //         throw new ListEmptyException("Indice fuera de rango");
    //     } else if (pos == 0) {
    //         return deleteFirst();
    //     } else if (length.intValue() == pos.intValue()) {
    //         return deleteLast();
    //     } else {
    //         Node<E> preview = getNode(pos - 1);
    //         Node<E> actualy = getNode(pos);
    //         E element = preview.getData();
    //         Node<E> next = actualy.getNext();
    //         actualy = null;
    //         preview.setNext(next);
    //         length--;
    //         return element;
    //     }
    // }

    public E[] toArray(){
        Class clazz = null;
        E[] matriz = null;
        if (this.length > 0) {
            clazz = head.getData().getClass();
            matriz = (E[]) java.lang.reflect.Array.newInstance(clazz, this.length);
            Node<E> aux = head;
            for (int i = 0; i < length; i ++){
                matriz[i] = aux.getData();
                aux = aux.getNext();
            }
        }
        return matriz;
    }

    public Linkendlist<E> toList(E[] matriz){
        clear();
        for (int i = 0; i < matriz.length; i++) {
            this.add(matriz[i]);   
        }
        return this;
    }

    public static void main(String[] args) throws ListEmptyException {
        Linkendlist<Double> lista = new Linkendlist<>();
        try {
            System.out.println("Hola");
            // lista.update(10.00, 0);

            lista.add(56.7);
            lista.add(65.7);
            lista.add(1.0, 0);
            lista.add(4.7);
            // System.out.println(lista.print());
            lista.add(9.0, 3);
            System.out.println(lista.print());
            System.out.println(lista.get(lista.getLength()-1));
            System.out.println("Actualizar");
            lista.update(10.00, 3);
            System.out.println(lista.print());
            // lista.delete(4);
            

        } catch (Exception e) {
            System.out.println("Error: " + e);
            // TODO: handle exception
        }
        System.out.println(lista.print());
        System.out.println("Final");
    }

}
