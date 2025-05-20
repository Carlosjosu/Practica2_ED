package com.unl.estrdts.base.controller.services;

import java.util.Arrays;
import java.util.List;

import com.unl.estrdts.base.controller.dao.dao_models.DaoArtista;
import com.unl.estrdts.base.models.Artista;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

@BrowserCallable
@AnonymousAllowed
public class ArtistaService {

    private DaoArtista db;
    public ArtistaService() {
        db = new DaoArtista();
    }

    public List<Artista> lisAllArtista(){
        return Arrays.asList(db.listAll().toArray());
        
    }
    
}
