package com.unl.estrdts.base.controller.DataStruc.stack;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;

public class StackImpementation<E> extends Linkendlist<E>{
    private Integer top;

    //getter 
    public Integer getTop() {
        return top;
    }
    
    public StackImpementation(Integer top) {
       
        this.top = top;
    }

    protected boolean isFull() {
        return getLength() > this.top;
    }

    protected void push(E info) throws Exception {
        if (!isFull()) {
            add( info, 0);
        } else {
            throw new ArrayIndexOutOfBoundsException("Stack is full");
        }
    }

    protected E pop()throws Exception {
        return deleteFirst(); 
    }

    
}
