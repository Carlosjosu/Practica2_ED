package com.unl.estrdts.base.controller.dao.dao_models;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;
import com.unl.estrdts.base.controller.dao.AdapterDao;
import com.unl.estrdts.base.models.Artista_Banda;
import com.unl.estrdts.base.models.RolArtistaEnum;


public class DaoArtista_Banda extends AdapterDao<Artista_Banda> {
    private Artista_Banda obj;
    private Linkendlist<Artista_Banda> aux;


    public DaoArtista_Banda() {
        super(Artista_Banda.class);
        // TODO Auto-generated constructor stub
    }

    // getter and setter
    public Artista_Banda getObj() {
        if (obj == null) {
            this.obj = new Artista_Banda();

        }
        return this.obj;
    }

    public void setObj(Artista_Banda obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(this.listAll().getLength() + 1);
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


    public static void main(String[] args) {
        DaoArtista_Banda da = new DaoArtista_Banda();
        da.getObj().setId(da.listAll().getLength() + 1);
      
        da.getObj().setId_artista(1);
        da.getObj().setId_banda(1);
        da.getObj().setRol(RolArtistaEnum.BATERISTA);
        if (da.save()) {
            System.out.println("Guardado");
        } else {
            System.out.println("Error al guardar");

        }
       
    }

}
