/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unl.pratica.base.controller.dao.dao_models;

import java.util.Date;

import com.unl.pratica.base.controller.dao.AdapterDao;
import com.unl.pratica.base.models.Banda;

/**
 *
 * @author maria
 */
public class DaoBanda extends AdapterDao<Banda>{
    private Banda obj;
    
    public DaoBanda(){
        super(Banda.class);
    }
    
    public Banda getObj() {
        if(obj == null)
            this.obj = new Banda();
        return obj;
    }

    public void setObj(Banda obj) {
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

     public static void main(String[] args) {
        DaoBanda da = new DaoBanda();
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNombre("Sin Bandera");
        da.getObj().setFecha(new Date());
        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
        da.setObj(null);
        da.getObj().setId(da.listAll().getLength() + 1);
        da.getObj().setNombre("Nirvana");
        da.getObj().setFecha(new Date());
        if (da.save())
            System.out.println("GUARDADO");
        else
            System.out.println("Hubo un error");
    }

    
}
