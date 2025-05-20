package com.unl.estrdts.base.controller.services;

import java.util.Arrays;
import java.util.List;

import com.unl.estrdts.base.controller.dao.dao_models.DaoAlbum;
import com.unl.estrdts.base.models.Album;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

@BrowserCallable
@AnonymousAllowed
public class AlbumService {
    private DaoAlbum db;

    public AlbumService() {
        db = new DaoAlbum();
    }

    public List<Album> listAllAlbum() {
        return Arrays.asList(db.listAll().toArray());
    }

    public void createAlbum(String nombre) {
        Album album = new Album();
        album.setNombre(nombre);

        List<Album> albumes = Arrays.asList(db.listAll().toArray());
        int maxId = albumes.stream()
                .mapToInt(a -> a.getId() != null ? a.getId() : 0)
                .max()
                .orElse(0);
        album.setId(maxId + 1);

        db.setObj(album);
        db.save();
    }
}