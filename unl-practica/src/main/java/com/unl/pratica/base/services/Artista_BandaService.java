package com.unl.pratica.base.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.unl.pratica.base.controller.dao.dao_models.DaoArtista;
import com.unl.pratica.base.controller.dao.dao_models.DaoArtista_Banda;
import com.unl.pratica.base.controller.dao.dao_models.DaoBanda;
import com.unl.pratica.base.models.Artista_Banda;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

@BrowserCallable
@AnonymousAllowed
public class Artista_BandaService {
    private DaoArtista_Banda db;

    public Artista_BandaService(){
        db = new DaoArtista_Banda();
    }

    public List<HashMap> listAll(){
        List<HashMap> lista = new ArrayList<>();
        if(!db.listAll().isEmpty()){
            Artista_Banda [] arreglo = db.listAll().toArray();
            DaoArtista da = new DaoArtista();
            DaoBanda db = new DaoBanda();
            for(int i = 0; i < arreglo.length; i++){
                HashMap<String, String> aux = new HashMap<>();
                aux.put("id", arreglo[i].getId().toString(i));
                aux.put("rol", arreglo[i].getRol().toString());
                aux.put("artista", da.listAll().get(arreglo[i].getId_artista()-1).getNombre());
                aux.put("banda", db.listAll().get(arreglo[i].getId_banda()-1).getNombre());
                lista.add(aux);
            }
        }
        return lista;
    }
}
