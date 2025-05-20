package com.unl.estrdts.base.models;

public class Artista_Banda {
    private Integer id;
    private RolArtistaEnum rol;
    private Integer id_artista;
    private Integer id_banda;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolArtistaEnum getRol() {
        return rol;
    }

    public void setRol(RolArtistaEnum rol) {
        this.rol = rol;
    }

    public Integer getId_artista() {
        return id_artista;
    }

    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
    }

    public Integer getId_banda() {
        return id_banda;
    }

    public void setId_banda(Integer id_banda) {
        this.id_banda = id_banda;
    }

    

}
