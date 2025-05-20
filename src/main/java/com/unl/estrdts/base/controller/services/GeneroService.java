package com.unl.estrdts.base.controller.services;

import java.util.Arrays;
import java.util.List;

import com.unl.estrdts.base.controller.dao.dao_models.DaoGenero;
import com.unl.estrdts.base.models.Genero;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

@BrowserCallable
@AnonymousAllowed
public class GeneroService {
    private DaoGenero db;

    public GeneroService() {
        db = new DaoGenero();
    }

    public List<Genero> listAllGenero() {
        return Arrays.asList(db.listAll().toArray());
    }

    public void createGenero(String nombre) throws Exception {
        if (nombre != null && nombre.trim().length() > 0) {
            db.getObj().setId(db.listAll().getLength() + 1);
            db.getObj().setNombre(nombre);
            if (!db.save()) {
                throw new Exception("Error al guardar el género");
            }
        } else {
            throw new Exception("Nombre requerido");
        }
    }

    public void updateGenero(Integer id, String nombre) throws Exception {
        if (id != null && id > 0 && nombre != null && nombre.trim().length() > 0) {
            db.setObj(db.listAll().get(id - 1));
            db.getObj().setNombre(nombre);
            if (!db.update(id - 1)) {
                throw new Exception("Error al actualizar el género");
            }
        } else {
            throw new Exception("Datos insuficientes para actualizar");
        }
    }
}
