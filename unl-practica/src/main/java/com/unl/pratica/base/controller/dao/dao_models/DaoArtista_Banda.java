/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.models.Artista_Banda;

/**
 *
 * @author maria
 */
public class DaoArtista_Banda extends AdapterDao<Artista_Banda> {
    private Artista_Banda obj;
    
    public DaoArtista_Banda(){
        super(Artista_Banda.class);
    }

    public Artista_Banda getObj() {
        if(obj == null)
            this.obj = new Artista_Banda();
        return this.obj;
    }

    public void setObj(Artista_Banda obj) {
        this.obj = obj;
    }
    
    public Boolean save(){
        try {
        obj.setId(listAll().getLength()+1);
        this.persist(obj);
        return true;
        }catch(Exception e){
            return false;
        }
    }    
        public Boolean update(Integer pos){
        try {
        this.update(obj, pos);
        return true;
        }catch(Exception e){
            return false;
        }
    }
}
