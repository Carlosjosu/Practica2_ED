package com.unl.estrdts.base.controller.DataStruc.queque;
public class Queue<E> {
    private QuequeIMplementatioin<E> queue;

    public Queue(Integer top){
        queue = new QuequeIMplementatioin<>(top);

    }

    public Boolean queue(E data){
        try {
            queue.queue(data);
            return true;
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        }
    }

    public E dequeue() {
        try {
            return queue.dequeue();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public Boolean isFullQueue(){
        return queue.isFullQueue();
    }

    public Integer top(){
        return queue.getTop();
    }

    public Integer size(){
        return queue.getLength();
    }
}
