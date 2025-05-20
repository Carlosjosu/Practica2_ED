package com.unl.estrdts.base.controller.DataStruc.stack;

public class Stack<E> {
    private StackImpementation<E> stack;

    public Stack(Integer top){
        stack = new StackImpementation<>(top);

    }

    public Boolean push(E data){
        try {
            stack.push(data);
            return true;
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        }
    }

    public E pop() {
        try {
            return stack.pop();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public Boolean isFullStack(){
        return stack.isFull();
    }

    public Integer top(){
        return stack.getTop();
    }

    public Integer size(){
        return stack.getLength();
    }
}
