package com.unl.estrdts.base.controller;

import com.unl.estrdts.base.models.Complejo;

public class ComplejoController {
    private Complejo ca;
    private Complejo cb;
    private Complejo[][] matriz;

    // getters and setters
    public Complejo getCa() {
        if (ca == null) {
            ca = new Complejo();
        }
        return ca;
    }

    public void setCa(Complejo ca) {
        this.ca = ca;
    }

    public Complejo getCb() {
        if (cb == null) {
            cb = new Complejo();
        }
        return cb;
    }   

    public void setCb(Complejo cb) {
        this.cb = cb;
    }

    public Complejo[][] getComplejo() {
        if (matriz == null) {
            matriz = new Complejo[5][3];
            for (int i = 0; i < matriz.length; i++) {
                Float nu = Float.parseFloat(String.valueOf((Math.random() * 100)));
                Float im = Float.parseFloat(String.valueOf((Math.random() * 100)));
                Float nub = Float.parseFloat(String.valueOf((Math.random() * 100)));
                Float imb = Float.parseFloat(String.valueOf((Math.random() * 100)));

                matriz[i][0] = new Complejo(nu, im);
                matriz[i][1] = new Complejo(nub, imb);
                matriz[i][2] = new Complejo();
            }
        }
        return matriz;
    }

    public void setComplejo(Complejo[][] matriz) {
        this.matriz = matriz;
    }

    public Complejo add() {
        Complejo C = new Complejo();
        C.setNum(ca.getNum() + cb.getNum());
        C.setIma(ca.getIma() + cb.getIma());
        return new Complejo();
    }

    public Complejo substract() {
        Complejo C = new Complejo();
        C.setNum(ca.getNum() - cb.getNum());
        C.setIma(ca.getIma() - cb.getIma());
        return new Complejo();
    }

    public Complejo mult() {
        Complejo C = new Complejo();
        C.setNum(ca.getNum() * cb.getNum());
        C.setIma(ca.getIma() * cb.getIma());
        return new Complejo();
    }

    public Complejo div() {
        Complejo C = new Complejo();
        C.setNum(ca.getNum() / cb.getNum());
        C.setIma(ca.getIma() / cb.getIma());
        return new Complejo();
    }

    // public Complejo[][] operar() {

    // }

}
