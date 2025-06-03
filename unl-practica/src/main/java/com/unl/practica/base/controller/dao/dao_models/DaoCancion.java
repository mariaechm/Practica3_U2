/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.practica.base.controller.dao.dao_models;

import com.unl.practica.base.controller.dao.AdapterDao;
import com.unl.practica.base.models.Cancion;

/**
 *
 * @author maria
 */
public class DaoCancion extends AdapterDao <Cancion> {
    private Cancion obj;

    public DaoCancion(){
        super(Cancion.class);
    }

    public Cancion getObj() {
        if (obj == null)
            this.obj = new Cancion();
        return this.obj;
    }

    public void setObj(Cancion obj) {
        this.obj = obj;
    }

    public Boolean save(){
        try {
            obj.setId(listAll().getLength()+1);
            this.persist(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

     public Boolean update(Integer pos){
        try {
            this.update(obj, pos);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
    
}
