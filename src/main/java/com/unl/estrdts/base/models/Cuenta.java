package com.unl.estrdts.base.models;

public class Cuenta {
    private Integer id;
    private String correo;
    private String clave;
    private Boolean estado;
    private Integer id_Persona;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getId_Persona() {
        return id_Persona;
    }
    
    public void setId_Persona(Integer id_Persona) {
        this.id_Persona = id_Persona;
    }


}
