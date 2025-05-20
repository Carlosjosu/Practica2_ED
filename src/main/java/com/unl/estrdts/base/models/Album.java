package com.unl.estrdts.base.models;

import java.util.Date;

public class Album {
    private Integer id;
    private String nombre;
    private Date fecha;
    private Integer id_Banda;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Integer getId_Banda() {
        return id_Banda;
    }

    public void setId_Banda(Integer id_Banda) {
        this.id_Banda = id_Banda;
    }
}
