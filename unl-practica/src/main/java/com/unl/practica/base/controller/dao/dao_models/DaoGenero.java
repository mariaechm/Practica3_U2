/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.practica.base.controller.dao.dao_models;

import com.unl.practica.base.controller.dao.AdapterDao;
import com.unl.practica.base.models.Genero;

/**
 *
 * @author maria
 */
public class DaoGenero extends AdapterDao<Genero> {
    private Genero obj;

    public DaoGenero(){
        super(Genero.class);
    }

    public Genero getObj() {
        if (obj == null)
            this.obj = new Genero();
        return this.obj;
    }

    public void setObj(Genero obj) {
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
