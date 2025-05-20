package com.unl.estrdts.base.models;

public class Complejo {
    private Float num;
    private Float ima;

    public Complejo(Float num, float ima) {
        this.num = num;
        this.ima = ima;
    }

    public Complejo() {
    }

    // getters and setters
    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public Float getIma() {
        return ima;
    }

    public void setIma(Float ima) {
        this.ima = ima;
    }

}
