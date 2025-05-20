package com.unl.estrdts.base.controller.DataStruc.queque;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;

public class QuequeIMplementatioin<E> extends Linkendlist<E>{
    private Integer top;

    //getter 
    public Integer getTop() {
        return top;
    }
    
    public QuequeIMplementatioin(Integer top) {
       
        this.top = top;
    }

    protected boolean isFullQueue() {
        return this.top >= getLength();
    }

    protected void queue(E info) throws Exception {
        if (!isFullQueue()) {
            add( info, 0);
        } else {
            throw new ArrayIndexOutOfBoundsException("Queque is full");
        }
    }

    protected E dequeue()throws Exception {
        return deleteFirst(); 
    }

    
}
