package com.unl.estrdts.base.controller.dao.dao_models;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;
import com.unl.estrdts.base.controller.dao.AdapterDao;
import com.unl.estrdts.base.models.Cuenta;

public class DaoCuenta extends AdapterDao<Cuenta> {
    private Cuenta obj;
    private Linkendlist<Cuenta> aux;

    public DaoCuenta() {
        super(Cuenta.class);
        // TODO Auto-generated constructor stub
    }

    // getter and setter
    public Cuenta getObj() {
        if (obj == null) {
            this.obj = new Cuenta();

        }
        return this.obj;
    }

    public void setObj(Cuenta obj) {
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
    public Linkendlist<Cuenta> getListAll() {
        if (aux == null) {
            this.aux = listAll();
        }
        return aux;
    }


    public static void main(String[] args) {
        DaoCuenta da = new DaoCuenta();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setCorreo("aaa@gmail.com");
        da.getObj().setClave("123456");
        da.getObj().setEstado(true);
        da.getObj().setId_Persona(1);
        if (da.save()) {
            System.out.println("Guardado");
        } else {
            System.out.println("Error al guardar");

        }
        
    }

}
