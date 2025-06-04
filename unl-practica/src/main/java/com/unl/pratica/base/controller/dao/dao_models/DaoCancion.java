/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.models.Cancion;
import com.unl.pratica.base.models.TipoArchivoEnum;

/**
 *
 * @author maria
 */
public class DaoCancion extends AdapterDao<Cancion>{
    private Cancion obj;
    
    public DaoCancion(){
        super(Cancion.class);
    }
    
    public Cancion getObj() {
        if(obj== null)
            this.obj = new Cancion();
        return obj;
    }

    public void setObj(Cancion obj) {
        this.obj = obj;
    }
    
    public Boolean save(){
        try{
            if (obj.getId() == null) {
                obj.setId(listAll().getLength());
            }
            obj.setId(this.listAll().getLength());
            this.persist(obj);
            return true;
        }catch(Exception e){
            return false;
        }
    }  
    
    public Boolean update(Integer pos){
        try{
            this.update(obj, pos);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
