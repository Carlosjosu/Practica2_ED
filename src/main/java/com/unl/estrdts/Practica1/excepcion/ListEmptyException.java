package com.unl.estrdts.Practica1.excepcion;

public class ListEmptyException extends Exception {
    /**
     * Creates a new instance of <code>EmptyException</code> without detail
     * message.
     */
    public ListEmptyException() {
    }

    /**
     * Constructs an instance of <code>EmptyException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ListEmptyException(String msg) {
        super(msg);
    }
}
