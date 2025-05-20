package com.unl.estrdts.base.controller.dao.dao_models;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;
import com.unl.estrdts.base.controller.dao.AdapterDao;
import com.unl.estrdts.base.models.Artista;


public class DaoArtista extends AdapterDao<Artista> {
    private Artista obj;
    private Linkendlist<Artista> aux;

    public DaoArtista() {
        super(Artista.class);
        // TODO Auto-generated constructor stub
    }

    // getter and setter
    public Artista getObj() {
        if (obj == null) {
            this.obj = new Artista();

        }
        return this.obj;
    }

    public void setObj(Artista obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            this.persist(obj);
            return true;
        } catch (Exception e) {
            
            return false;
            // TODO: handle exception
        }
    }

    public Boolean update(Integer pos) {
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {
            
            return false;
            // TODO: handle exception
        }
    }
    public Linkendlist<Artista> getListAll() {
        if (aux == null) {
            this.aux = listAll();
        }
        return aux;
    }


    public static void main(String[] args) {
        DaoArtista da = new DaoArtista();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNacionalidad("Ecuatoriana");
        da.getObj().setNombres("Viviana Cordova");
        if (da.save()) {
            System.out.println("Guardado");
        } else {
            System.out.println("Error al guardar");

        }
        da.setObj(null);
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNacionalidad("Ecuatoriana");
        da.getObj().setNombres("Juan Veintmilla");
        if (da.save()) {
            System.out.println("Guardado");
        } else {
            System.out.println("Error al guardar");

        }
    }

}
