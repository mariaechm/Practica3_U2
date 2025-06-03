package com.unl.practica.base.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.github.javaparser.quality.NotNull;
import com.unl.practica.base.controller.dao.dao_models.DaoBanda;
import com.unl.practica.base.models.Banda;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import jakarta.validation.constraints.NotEmpty;

@BrowserCallable
@AnonymousAllowed
public class BandaServices {
    private DaoBanda db;

    public BandaServices(){
        db = new DaoBanda();
    }

    public void createBanda(@NotEmpty String nombre, @NotNull Date fecha) throws Exception{
        if (nombre.trim().length() > 0 && fecha.toString().length()>0){
            db.getObj().setNombre(nombre);
            db.getObj().setFecha(fecha);
            if(!db.save())
                throw new Exception("Nose se pudo guardar los datos de banda");
        }
    }

    public List<Banda> listAllBanda(){
        return Arrays.asList(db.listAll().toArray());

    }
    
}
