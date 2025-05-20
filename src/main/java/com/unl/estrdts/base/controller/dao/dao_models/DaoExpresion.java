package com.unl.estrdts.base.controller.dao.dao_models;

import com.unl.estrdts.base.controller.DataStruc.stack.Stack;
import com.unl.estrdts.base.controller.dao.AdapterDao;
import com.unl.estrdts.base.models.Expresion;

public class DaoExpresion extends AdapterDao<Expresion> {
    private Expresion obj;
    public DaoExpresion() {
        super(Expresion.class);
    }
    public Expresion getObj() {
        if (obj == null)
            this.obj = new Expresion();
        return this.obj;
    }

    public void setObj(Expresion obj) {
        this.obj = obj;
    }

    public Boolean save() {
        try {
            obj.setId(listAll().getLength()+1);
            obj.setIsCorrecto(validar());
            this.persist(obj);
            return true;
        } catch (Exception e) {
            //TODO
            return false;
            // TODO: handle exception
        }
    }

    public Boolean validar() {
        Stack<String> pila = new Stack<>(100);
        String aux = obj.getExpresion().trim();
        System.out.println("***** ******"+aux);
        for(int i = 0; i < aux.length();i++) {            
            if(aux.charAt(i) == '<') {                
                pila.push("<");
                //System.out.println("Agregue "+aux.charAt(i)+" -- "+pila.size());
                System.out.println("add "+aux.charAt(i)+" -- "+pila.size());
            } else if(aux.charAt(i) == '>') {
                if(pila.pop()==null){
                    return false;
                    //break;
                }else{
                    pila.pop();
                    System.out.println("delete "+aux.charAt(i)+" -- "+pila.size());
                }
                
            }
        }
        System.out.println("PILA "+pila.size());
        return pila.size() <= 0;

    }
}
