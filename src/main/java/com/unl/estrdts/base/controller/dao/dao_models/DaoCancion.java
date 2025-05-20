package com.unl.estrdts.base.controller.dao.dao_models;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;
import com.unl.estrdts.base.controller.dao.AdapterDao;
import com.unl.estrdts.base.models.Cancion;

public class DaoCancion extends AdapterDao<Cancion> {
    private Cancion obj;
    private Linkendlist<Cancion> aux;

    public DaoCancion() {
        super(Cancion.class);
        // TODO Auto-generated constructor stub
    }

    // getter and setter
    public Cancion getObj() {
        if (obj == null) {
            this.obj = new Cancion();

        }
        return this.obj;
    }

    public void setObj(Cancion obj) {
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

    public Linkendlist<Cancion> getListAll() {
        if (aux == null) {
            this.aux = listAll();
        }
        return aux;
    }

}
