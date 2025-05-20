package com.unl.estrdts.base.models;

public class Expresion {
    private Integer id;
    private String expresion;
    private Boolean isCorrecto;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpresion() {
        return this.expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public Boolean getIsCorrecto() {
        return this.isCorrecto;
    }

    public void setIsCorrecto(Boolean isCorrecto) {
        this.isCorrecto = isCorrecto;
    }
 
}
