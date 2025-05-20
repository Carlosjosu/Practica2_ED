package com.unl.estrdts.base.controller.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.unl.estrdts.base.controller.DataStruc.List.Linkendlist;
import com.unl.estrdts.base.controller.dao.dao_models.DaoArtista;
import com.unl.estrdts.base.controller.dao.dao_models.DaoArtista_Banda;
import com.unl.estrdts.base.controller.dao.dao_models.DaoBanda;
import com.unl.estrdts.base.controller.excepcion.ListEmptyException;
import com.unl.estrdts.base.models.Artista_Banda;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

@BrowserCallable
@AnonymousAllowed
public class ArtistaBandaService {

    private DaoArtista_Banda db;
    public ArtistaBandaService(){
        db = new DaoArtista_Banda();
    }
    public List<HashMap> listAll() throws ListEmptyException{
        List<HashMap> lista = new ArrayList<>();
        if(!db.listAll().isEmpty()) {
            Artista_Banda [] arreglo = db.listAll().toArray();
            DaoArtista da = new DaoArtista();
            DaoBanda db = new DaoBanda();
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("id", arreglo[i].getId().toString(i));
                aux.put("rol", arreglo[i].getRol().toString());
                aux.put("artista", da.listAll().get(arreglo[i].getId_artista() -1).getNombres());
                aux.put("banda", db.listAll().get(arreglo[i].getId_banda() -1).getNombre());
                lista.add(aux);
            }
        }
        return lista;
    }

    public List<Artista_Banda> lisAllArtista(){
        return Arrays.asList(db.listAll().toArray());
        
    }
    
}