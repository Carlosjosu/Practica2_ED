package com.unl.estrdts.base.controller.dao.dao_models;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;
import com.unl.estrdts.base.controller.dao.AdapterDao;
import com.unl.estrdts.base.models.Genero;

public class DaoGenero extends AdapterDao<Genero> {
    private Genero obj;
    private Linkendlist<Genero> aux;

    public DaoGenero() {
        super(Genero.class);
        // TODO Auto-generated constructor stub
    }

    // getter and setter
    public Genero getObj() {
        if (obj == null) {
            this.obj = new Genero();

        }
        return this.obj;
    }

    public void setObj(Genero obj) {
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
    public Linkendlist<Genero> getListAll() {
        if (aux == null) {
            this.aux = listAll();
        }
        return aux;
    }
   

    public static void main(String[] args) {
        DaoGenero da = new DaoGenero();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNombre("Genero 1");
        if (da.save()) {
            System.out.println("Guardado");
        } else {
            System.out.println("Error al guardar");

        }
      
    }

   

    

}
