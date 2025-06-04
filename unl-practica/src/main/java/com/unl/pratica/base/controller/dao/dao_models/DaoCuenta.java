/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.models.Cuenta;

/**
 *
 * @author maria
 */
public class DaoCuenta extends AdapterDao<Cuenta>{
    private Cuenta obj;
    
    public DaoCuenta(){
        super(Cuenta.class);
    }

    public Cuenta getObj() {
        if(obj== null)
            this.obj = new Cuenta();
        return this.obj;
    }

    public void setObj(Cuenta obj) {
        this.obj = obj;
    }
    
    public Boolean save(){
        try{
            obj.setId(listAll().getLength()+1);
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
