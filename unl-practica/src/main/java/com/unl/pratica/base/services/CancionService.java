package com.unl.pratica.base.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unl.pratica.base.controller.dao.dao_models.DaoAlbum;
import com.unl.pratica.base.controller.dao.dao_models.DaoCancion;
import com.unl.pratica.base.controller.dao.dao_models.DaoGenero;
import com.unl.pratica.base.controller.data_struct.list.LinkedList;
import com.unl.pratica.base.models.Album;
import com.unl.pratica.base.models.Artista;
import com.unl.pratica.base.models.Cancion;
import com.unl.pratica.base.models.Genero;
import com.unl.pratica.base.models.TipoArchivoEnum;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@Transactional(propagation = Propagation.REQUIRES_NEW)
@AnonymousAllowed
public class CancionService {
    private DaoCancion dc;

    public CancionService() {
        dc = new DaoCancion();
    }

    public void createCancion(@NotEmpty String nombre, Integer id_genero, Integer duracion, @NotEmpty String url,
            @NotEmpty String tipo, Integer id_album) throws Exception {
        if (nombre.trim().length() > 0 && url.trim().length() > 0 && tipo.trim().length() > 0 && id_genero > 0
                && duracion > 0 && id_album > 0) {
            dc.getObj().setNombre(nombre);
            dc.getObj().setId_genero(id_genero);
            dc.getObj().setDuracion(duracion);
            dc.getObj().setId_album(id_album);
            dc.getObj().setTipo(TipoArchivoEnum.valueOf(tipo));
            dc.getObj().setUrl(url);
            if (!dc.save())
                throw new Exception("No se puede guaradar los datos de cancion");
        }
    }

    public void updateCancion(Integer id, @NotEmpty String nombre, Integer id_genero, Integer duracion,
            @NotEmpty String url,
            @NotEmpty String tipo, Integer id_album) throws Exception {
        if (nombre.trim().length() > 0 && url.trim().length() > 0 && tipo.trim().length() > 0 && id_genero > 0
                && duracion > 0 && id_album > 0) {
            dc.setObj(dc.listAll().get(id - 1));
            dc.getObj().setNombre(nombre);
            dc.getObj().setId_genero(id_genero);
            dc.getObj().setDuracion(duracion);
            dc.getObj().setId_album(id_album);
            dc.getObj().setTipo(TipoArchivoEnum.valueOf(tipo));
            dc.getObj().setUrl(url);
            if (!dc.update(id - 1))
                throw new Exception("Nose pudo modificar los datos de cancion");
        }
    }

    public List<HashMap> listGenero() {
        List<HashMap> list = new ArrayList<>();
        DaoGenero da = new DaoGenero();
        if (!da.listAll().isEmpty()) {
            Genero[] arreglo = da.listAll().toArray();
            for (int i = 0; i < arreglo.length; i++) {
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
        for (TipoArchivoEnum r : TipoArchivoEnum.values()) {
            list.add(r.toString());
        }
        return list;
    }

    public List<HashMap> listAlbumCombo() {
        List<HashMap> list = new ArrayList<>();
        DaoAlbum da = new DaoAlbum();
        if (!da.listAll().isEmpty()) {
            Album[] arreglo = da.listAll().toArray();
            for (int i = 0; i < arreglo.length; i++) {
                HashMap<String, String> aux = new HashMap<>();
                aux.put("value", arreglo[i].getId().toString(i));
                aux.put("label", arreglo[i].getNombre());
                list.add(aux);
            }
        }
        return list;
    }

    public List<HashMap> listAll() throws Exception { // listAll
        return Arrays.asList(dc.all().toArray());
    }

    /*
     * public List<Cancion> list(Pageable pageable) {
     * return Arrays.asList(dc.listAll().toArray());
     */

    public List<HashMap> order(String attribute, Integer type) throws Exception {
        boolean isNumero = attribute.equalsIgnoreCase("duracion") || attribute.equalsIgnoreCase("id");
        return Arrays.asList(dc.orderQ(type, attribute, isNumero).toArray());
    }

    public List<HashMap> buscarLineal(String attribute, String text, Integer type) throws Exception {
        LinkedList<HashMap<String, String>> lista = dc.buscarLineal(attribute, text, type);
        if (!lista.isEmpty())
            return Arrays.asList(lista.toArray());
        else
            return new ArrayList<>();
    }

    public List<HashMap> buscarBinariaLineal(String attribute, String text, Integer type) throws Exception {
        LinkedList<HashMap<String, String>> lista = dc.buscarBinariaLineal(attribute, text, type);
        if (!lista.isEmpty())
            return Arrays.asList(lista.toArray());
        else
            return new ArrayList<>();
    }

}
