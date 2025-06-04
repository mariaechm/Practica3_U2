package com.unl.pratica.base.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.unl.pratica.base.controller.dao.dao_models.DaoArtista;
import com.unl.pratica.base.models.Artista;
import com.unl.pratica.base.models.RolArtistaEnum;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.mappedtypes.Pageable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@BrowserCallable
@AnonymousAllowed
public class ArtistaService {
    private DaoArtista da;

    public ArtistaService(){
        da = new DaoArtista();
    }

    public void createArtista(@NotEmpty String nombre, @NotEmpty String nacionalidad) throws Exception{
        da.getObj().setNacionalidad(nacionalidad);
        da.getObj().setNombre(nombre);
        if(!da.save())
            throw new Exception("No se puedo guaradar datos de artista");
    }
 
    public void updateArtista(@NotNull Integer id, @NotEmpty String nombre, @NotEmpty String nacionalidad) throws Exception{
        da.setObj(da.listAll().get(id));
        da.getObj().setNacionalidad(nacionalidad);
        da.getObj().setNombre(nombre);
        if(!da.update(id))
            throw new Exception("No se pudo actualizar los datos de Artistas");
    }

    public List<Artista> list(Pageable pageable){
        return Arrays.asList(da.listAll().toArray());
    }

    public List<Artista> listAll(){
        return (List<Artista>) Arrays.asList(da.listAll().toArray());
    }

    public List<String> listPaises(){
        List<String> nacionalidades = new ArrayList<>();
         String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            Locale locale = new Locale("", countryCode);
            nacionalidades.add(locale.getDisplayCountry());
        }
        return nacionalidades;
    }

    public List<String> listRolArtista() {
        List<String> lista = new ArrayList<>();
        for(RolArtistaEnum r: RolArtistaEnum.values()) {
            lista.add(r.toString());
        }        
        return lista;
    }

}
