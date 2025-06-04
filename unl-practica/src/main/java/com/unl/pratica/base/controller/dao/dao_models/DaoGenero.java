/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.models.Genero;

/**
 *
 * @author maria
 */
public class DaoGenero extends AdapterDao<Genero>{
    private Genero obj;
    
    public DaoGenero(){
        super(Genero.class);
    }
    
    public Genero getObj() {
        if(obj == null)
            this.obj = new Genero();
        return this.obj;
    }

    public void setObj(Genero obj) {
        this.obj = obj;
    }
    
    public Boolean save(){
        try{
            obj.setId(listAll().getLength() + 1);
            this.persist(obj);
            return true;
        }catch (Exception  e){
            return false;
        }
    }
    
    public Boolean update(Integer pos){
        try{
            this.update(obj, pos);
            return true;
        }catch (Exception  e){
            return false;
        }
    }

     public static void main(String[] args) {
        DaoGenero de = new DaoGenero();
        de.getObj().setId(de.listAll().getLength() + 1);
        de.getObj().setNombre("Pop");
        if (de.save())
            System.out.println("GUARdeDO");
        else
            System.out.println("Hubo un error");
        de.setObj(null);
        de.getObj().setId(de.listAll().getLength() + 1);
        de.getObj().setNombre("Balada");
        if (de.save())
            System.out.println("GUARdeDO");
        else
            System.out.println("Hubo un error");
        de.setObj(null);
        de.getObj().setId(de.listAll().getLength() + 1);
        de.getObj().setNombre("Rock");
        if (de.save())
            System.out.println("GUARdeDO");
        else
            System.out.println("Hubo un error");
    }
}
