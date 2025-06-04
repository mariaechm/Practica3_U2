package com.unl.pratica.base.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.unl.pratica.base.controller.dao.dao_models.DaoAlbum;
import com.unl.pratica.base.controller.dao.dao_models.DaoCancion;
import com.unl.pratica.base.controller.dao.dao_models.DaoGenero;
import com.unl.pratica.base.models.Album;
import com.unl.pratica.base.models.Cancion;
import com.unl.pratica.base.models.Genero;
import com.unl.pratica.base.models.TipoArchivoEnum;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@AnonymousAllowed
public class CancionService {
    private DaoCancion dc;

    public CancionService(){
        dc = new DaoCancion();
    }
    
    public void createCancion(@NotEmpty String nombre, Integer id_genero, Integer duracion, @NotEmpty String url,
    @NotEmpty String tipo, Integer id_album) throws Exception {
        if (nombre.trim().length()> 0 && url.trim().length() > 0 && tipo.trim().length() > 0 && id_genero > 0 
            && duracion > 0 && id_album > 0) {
                dc.getObj().setNombre(nombre);
                dc.getObj().setId_genero(id_genero);
                dc.getObj().setDuracion(duracion);
                dc.getObj().setId_album(id_album);
                dc.getObj().setTipo(TipoArchivoEnum.valueOf(tipo));
                dc.getObj().setUrl(url);
                if(!dc.save())
                    throw new Exception("No se puede guaradar los datos de cancion");
        }
    }

    public void updateCancion(Integer id, @NotEmpty String nombre, Integer id_genero, Integer duracion, @NotEmpty String url,
    @NotEmpty String tipo, Integer id_album) throws Exception {
        if (nombre.trim().length()>0 && url.trim().length()>0 && tipo.trim().length() > 0 && id_genero > 0
            && duracion > 0 && id_album > 0) {
                dc.setObj(dc.listAll().get(id -1));
                dc.getObj().setNombre(nombre);
                dc.getObj().setId_genero(id_genero);
                dc.getObj().setDuracion(duracion);
                dc.getObj().setId_album(id_album);
                dc.getObj().setTipo(TipoArchivoEnum.valueOf(tipo));
                dc.getObj().setUrl(url);
                if (!dc.update(id -1))
                 throw new Exception("Nose pudo modificar los datos de cancion");
        }
    }

    public List<HashMap> listGenero() {
        List<HashMap> list = new ArrayList<>();
        DaoGenero da = new DaoGenero();
        if(!da.listAll().isEmpty()) {
            Genero [] arreglo = da.listAll().toArray();
            for(int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i));
                aux.put("label", arreglo[i].getNombre()); 
                list.add(aux);  
            }
        }
        return list;
    }

    public List<String> listTipo() {
        List<String> list = new ArrayList<>();
        for(TipoArchivoEnum r: TipoArchivoEnum.values()) {
            list.add(r.toString());
        }        
        return list;
    }
    
    public List<HashMap> listAlbumCombo(){
        List<HashMap> list = new ArrayList<>();
        DaoAlbum da = new DaoAlbum();
        if (!da.listAll().isEmpty()) {
            Album [] arreglo = da.listAll().toArray();
            for(int i = 0; i < arreglo.length; i ++){
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i));
                aux.put("label", arreglo[i].getNombre());   
                list.add(aux); 
            }
        }
        return list;
    }

    public List <HashMap> listCancion(){
        List<HashMap> list = new ArrayList<>();
        if (!dc.listAll().isEmpty()) {
            Cancion [] arreglo = dc.listAll().toArray();
            for (int i = 0; i < arreglo.length; i++){
                HashMap<String, String> aux = new HashMap<>();
                aux.put("1", arreglo[i].getId().toString(i));
                aux.put("nombre", arreglo[i].getNombre());
                aux.put("genero", new DaoGenero().listAll().get(arreglo[i].getId_genero() -1).getNombre());
                 aux.put("duracion", arreglo[i].getDuracion().toString());
                aux.put("album", new DaoAlbum().listAll().get(arreglo[i].getId_album() -1).getNombre());
              
                aux.put("id_genero", String.valueOf(new DaoGenero().listAll().get(arreglo[i].getId_genero() -1).getId()));
                aux.put("id_album", String.valueOf(new DaoAlbum().listAll().get(arreglo[i].getId_album() -1).getId()));

                aux.put("url", arreglo[i].getUrl());
                aux.put("tipo", arreglo[i].getTipo().toString());
                list.add(aux);
            }  
         }
         return list;
    } 
}

